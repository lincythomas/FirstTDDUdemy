package com.example.firsttdd.playlists

import android.util.Log
import com.example.firsttdd.playlist.PlayListApi
import com.example.firsttdd.playlist.PlayListRaw
import com.example.firsttdd.playlist.PlayListService
import com.example.firsttdd.recylerviewdata.PlayListItem
import com.example.firsttdd.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Retrofit
import java.lang.RuntimeException

class PlayListServiceShould : BaseUnitTest() {
    val api: PlayListApi = mock()
    val expected = mock<List<PlayListRaw>>()
    val exception = RuntimeException("No network connection")

    @Test
    fun fetchPlayListsFromApi() = runTest {
        val service = PlayListService(api)
        service.fetchPlayLists()

        verify(api, times(1)).fetchAllPlayLists()
    }

    @Test
    fun returnSuccessFulPlayList() = runTest {
        whenever(api.fetchAllPlayLists()).thenReturn(
            (expected)
        )
        val service = PlayListService(api)

//        assertEquals(expected, api.fetchAllPlayLists())
        assertEquals(Result.success(expected), service.fetchPlayLists().first())
    }

    @Test
    fun returnErrorWhenNoNetwork() = runTest {
        whenever(api.fetchAllPlayLists()).thenThrow(
            RuntimeException("Failing code")
        )
        val service = PlayListService(api)
        assertEquals("No network connection",
            service.fetchPlayLists().first().exceptionOrNull()?.message)
    }
}