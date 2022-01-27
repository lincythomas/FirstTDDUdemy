package com.example.firsttdd.playlists

import com.example.firsttdd.playlist.PlayListRaw
import com.example.firsttdd.recylerviewdata.PlayListItem
import javax.inject.Inject

class PlayListMapper @Inject constructor():Function1<List<PlayListRaw>,List<PlayListItem>> {
    override fun invoke(p1: List<PlayListRaw>): List<PlayListItem> {
        return ArrayList<PlayListItem>()
    }

}
