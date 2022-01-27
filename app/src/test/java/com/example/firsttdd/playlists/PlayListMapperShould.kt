package com.example.firsttdd.playlists

import com.example.firsttdd.R
import com.example.firsttdd.playlist.PlayListRaw
import com.example.firsttdd.utils.BaseUnitTest
import junit.framework.Assert.assertEquals
import org.junit.Test

class PlayListMapperShould : BaseUnitTest() {

    private val playListRaw = PlayListRaw("1", "da Name", "da Category")
    private val playListRawRock = PlayListRaw("1", "da Name", "rock")

    private val mapper = PlayListMapper()
    private val playLists = mapper(listOf(playListRaw))
    private val playListItem = playLists[0]
    private val playListRock = mapper(listOf(playListRawRock))[0]

    @Test
    fun keepSameId() {
        assertEquals(playListRaw.id, playListItem.id)
    }

    @Test
    fun keepSameName() {
        assertEquals(playListRaw.name, playListItem.name)
    }

    @Test
    fun keepSameCategory() {
        assertEquals(playListRaw.category, playListItem.category)
    }

    @Test
    fun mapDefaultImageNotRock() {
        assertEquals(R.drawable.ic_playlist, playListItem.drawable)
    }

    @Test
    fun mapRockImageWhenRockCategory() {
        assertEquals(R.drawable.ic_rock, playListRock.drawable)
    }
}