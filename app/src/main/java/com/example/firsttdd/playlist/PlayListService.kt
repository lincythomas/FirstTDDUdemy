package com.example.firsttdd.playlist

import com.example.firsttdd.recylerviewdata.PlayListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.RuntimeException
import javax.inject.Inject

class PlayListService @Inject constructor(
    private val api: PlayListApi
) {

    suspend fun fetchPlayLists(): Flow<Result<List<PlayListRaw>>> {
        return flow {
            emit(Result.success(api.fetchAllPlayLists()))
        }.catch {
            emit(Result.failure(RuntimeException("No network connection")))
        }
    }

}
