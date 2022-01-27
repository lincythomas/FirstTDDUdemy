package com.example.firsttdd

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class PlaylistFeature {

    val activityRule = ActivityTestRule(MainActivity::class.java)
        @Rule get

    @Test
    fun displayScreenTitle() {
        assertDisplayed("Playlists")
    }

    @Test
    fun displaysListOfPlaylists(){
        Thread.sleep(4002)
        assertRecyclerViewItemCount(R.id.playlist, 10)
        onView(allOf(withId(R.id.playlistName), isDescendantOfA(nthChildOf(withId(R.id.playlist),0))))
            .check(matches(withText("Hard Rock Cafe")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.playlistCategory), isDescendantOfA(nthChildOf(withId(R.id.playlist),0))))
            .check(matches(withText("rock")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.playlistImage), isDescendantOfA(nthChildOf(withId(R.id.playlist),1))))
            .check(matches(withDrawable(R.drawable.ic_playlist)))
            .check(matches(isDisplayed()))


    }

    @Test
    fun isLoaderShowingWhileFetchingPlayLists(){
        Thread.sleep(10)
        assertDisplayed(R.id.loader)
    }

    @Test
    fun hidesLoader(){
        Thread.sleep(4001)
        assertNotDisplayed(R.id.loader)
    }

    @Test
    fun displayRockImageForRockListItems(){
        Thread.sleep(4002)
        onView(allOf(withId(R.id.playlistImage), isDescendantOfA(nthChildOf(withId(R.id.playlist),0))))
            .check(matches(withDrawable(R.drawable.ic_rock)))
            .check(matches(isDisplayed()))
        onView(allOf(withId(R.id.playlistImage), isDescendantOfA(nthChildOf(withId(R.id.playlist),3))))
            .check(matches(withDrawable(R.drawable.ic_rock)))
            .check(matches(isDisplayed()))

    }


    fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("position $childPosition of parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                if (view.parent !is ViewGroup) return false
                val parent = view.parent as ViewGroup

                return (parentMatcher.matches(parent)
                        && parent.childCount > childPosition
                        && parent.getChildAt(childPosition) == view)
            }
        }
    }
}