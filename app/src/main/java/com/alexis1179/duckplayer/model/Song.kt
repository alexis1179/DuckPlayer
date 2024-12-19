package com.alexis1179.duckplayer.model

data class Song(
    val name: String,
    val path: String,
    val artist: String,
    val duration: Long,
    val year: Int,
    val album: String,
    val uri : String
)