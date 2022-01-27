package com.example.firsttdd.playlist

import com.example.firsttdd.playlists.PlayListMapper
import com.example.firsttdd.recylerviewdata.PlayListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.RuntimeException
import javax.inject.Inject

class PlayListRepository @Inject constructor(
    private val service: PlayListService,
    private val mapper: PlayListMapper
) {
    suspend fun getPlayLists(): Flow<Result<List<PlayListItem>>> =
        service.fetchPlayLists().map {
            if(it.isSuccess) {
                Result.success(mapper(it.getOrNull()!!))
            }else{
                Result.failure(it.exceptionOrNull()!!)
            }
        }


}
