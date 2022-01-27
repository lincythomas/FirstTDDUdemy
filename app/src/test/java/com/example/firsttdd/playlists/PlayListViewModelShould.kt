package com.example.firsttdd.playlists

import com.example.firsttdd.playlist.PlayListRepository
import com.example.firsttdd.playlist.PlayListViewModel
import com.example.firsttdd.recylerviewdata.PlayListItem
import com.example.firsttdd.utils.BaseUnitTest
import com.example.firsttdd.utils.captureValues
import com.example.firsttdd.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import java.lang.RuntimeException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PlayListViewModelShould : BaseUnitTest() {

    private val repository: PlayListRepository = mock()

    val playLists = mock<List<PlayListItem>>()
    val expected = Result.success(playLists)
    val exception = RuntimeException("something went wrong")

    @Test
    fun getPlayListsFromRepository() = runTest {

        val viewModel = mockSuccessFulCase()
        viewModel.playLists.getValueForTest()

        verify(repository, times(1)).getPlayLists()
    }

    @Test
    fun emitsPlayListFromRepository() = runTest {
        val viewModel = mockSuccessFulCase()
        assertEquals(expected, viewModel.playLists.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceivedError() = runTest {
        val viewModel = mockErrorCase()
        assertEquals(exception, viewModel.playLists.getValueForTest()?.exceptionOrNull())
    }

    private fun mockErrorCase(): PlayListViewModel {
        runBlocking {
            whenever(repository.getPlayLists())
                .thenReturn(flow { emit(Result.failure<List<PlayListItem>>(exception)) })
        }

        val viewModel = PlayListViewModel(repository)
        return viewModel
    }

    @Test
    fun showSpinnerWhileLoading() = runTest {
        val viewModel = mockSuccessFulCase()
        viewModel.loader.captureValues {
            viewModel.playLists.getValueForTest()
            assertEquals(true, values[0])
        }
    }

    @Test
    fun hideSpinnerAfterApiCompletion() = runTest {
        val viewModel = mockSuccessFulCase()
        viewModel.loader.captureValues {
            viewModel.playLists.getValueForTest()
            assertEquals(false, values.last())
        }
    }

    @Test
    fun hideSpinnerAfterApiFailed() = runTest {
        val viewModel = mockErrorCase()
        viewModel.loader.captureValues {
            viewModel.playLists.getValueForTest()
            assertEquals(false, values.last())
        }
    }

    private fun mockSuccessFulCase(): PlayListViewModel {
        runBlocking {
            whenever(
                repository.getPlayLists()
            )
                .thenReturn(flow {
                    emit(expected)
                })
        }

        val viewModel = PlayListViewModel(repository)
        return viewModel
    }
}