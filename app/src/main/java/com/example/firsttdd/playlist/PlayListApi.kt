package com.example.firsttdd.playlist

import com.example.firsttdd.recylerviewdata.PlayListItem
import retrofit2.http.GET

interface PlayListApi {
    @GET("playlists")
    suspend fun fetchAllPlayLists(): List<PlayListRaw>
}