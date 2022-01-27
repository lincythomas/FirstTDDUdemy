package com.example.firsttdd.unittests

import com.example.firsttdd.Engine
import com.example.firsttdd.utils.MainCoroutineScopeRule
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class EngineShould {

    private val engine = Engine()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    @Test
    fun turnOn() = runTest {
        engine.turnOn()
        assertTrue(engine.isTurnedOn)
    }

    @Test
    fun riseTempWhenTurnsOn() = runTest {
        val flow = engine.turnOn()
        val actual = flow.toList()
        assertEquals(listOf(25, 45, 95), actual)
    }
}