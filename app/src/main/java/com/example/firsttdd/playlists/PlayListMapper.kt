package com.example.firsttdd.playlists

import com.example.firsttdd.R
import com.example.firsttdd.playlist.PlayListRaw
import com.example.firsttdd.recylerviewdata.PlayListItem
import javax.inject.Inject

class PlayListMapper @Inject constructor() : Function1<List<PlayListRaw>, List<PlayListItem>> {
    override fun invoke(playListRaw: List<PlayListRaw>): List<PlayListItem> {
        return playListRaw.map {
            val image = when (it.category) {
                "rock" -> R.drawable.ic_rock
                else -> R.drawable.ic_playlist
            }

            PlayListItem(it.id, it.name, it.category, image)
        }
    }

}
