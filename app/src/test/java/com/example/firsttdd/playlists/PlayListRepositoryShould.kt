package com.example.firsttdd.playlists

import com.example.firsttdd.playlist.PlayListRaw
import com.example.firsttdd.playlist.PlayListRepository
import com.example.firsttdd.playlist.PlayListService
import com.example.firsttdd.recylerviewdata.PlayListItem
import com.example.firsttdd.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.RuntimeException

class PlayListRepositoryShould : BaseUnitTest() {
    private val service: PlayListService = mock()
    private val mapper: PlayListMapper = mock()
    private val playList = mock<List<PlayListItem>>()
    private val playListRaw = mock<List<PlayListRaw>>()
    private val exception = RuntimeException("No network connection")

    @Test
    fun getPlayListsFromService() = runTest {

        val repository = mockSuccessFulCase()
        repository.getPlayLists()
        verify(service, times(1)).fetchPlayLists()
    }

    @Test
    fun emitPlayListFromService() = runTest {
        val repository = mockSuccessFulCase()
        assertEquals(playList, repository.getPlayLists().first().getOrNull())
    }

    @Test
    fun propagateErrors() = runTest {
        val repository = mockFailureCase()
        assertEquals(exception, repository.getPlayLists().first().exceptionOrNull())
    }

    @Test
    fun delegateBusinessLogicToMapper() = runTest {
        val repository = mockSuccessFulCase()
        repository.getPlayLists().first()
        verify(mapper, times(1)).invoke(playListRaw)
    }

    private suspend fun mockFailureCase(): PlayListRepository {
        whenever(service.fetchPlayLists()).thenReturn(
            flow {
                emit(Result.failure<List<PlayListRaw>>(exception))
            })

        return PlayListRepository(service, mapper)
    }

    private suspend fun mockSuccessFulCase(): PlayListRepository {
        whenever(service.fetchPlayLists()).thenReturn(
            flow {
                emit(Result.success(playListRaw))
            }
        )

        whenever(mapper.invoke(playListRaw)).thenReturn(playList)

        return PlayListRepository(service, mapper)
    }
}