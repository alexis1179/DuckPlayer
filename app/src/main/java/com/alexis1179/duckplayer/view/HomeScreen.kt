package com.alexis1179.duckplayer.view

import android.media.MediaMetadataRetriever
import android.os.Environment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexis1179.duckplayer.model.Song
import com.alexis1179.duckplayer.utils.TimeFormat
import com.alexis1179.duckplayer.view.components.CurrentMusicControls
import com.alexis1179.duckplayer.view.components.SongListItem

@Composable
fun HomeScreen(){
    Column(modifier = Modifier
        .fillMaxHeight()
        .padding(4.dp)) {
        val songs = getAllSongs(".m4a")
        LazyColumn(modifier = Modifier.weight(1f)) {
            songs.forEach { song ->
                item {
                    SongListItem(name = song.name, artist = song.artist, duration = TimeFormat.formateMilliSeccond(song.duration),
                        uri = song.uri)
                } }
        }
        CurrentMusicControls()
    }
}

fun getAllSongs(extension: String): List<Song> {
    val directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)

    return directory?.listFiles { file ->
        file.isFile && file.extension == extension.removePrefix(".")
    }?.map {file ->
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(file.absolutePath)
        val artist = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
        val duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)?.toLongOrNull()
        val year = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_YEAR)?.toIntOrNull()
        val album = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)
        val uri = file.absolutePath
        retriever.release()
        Song(file.name, file.absolutePath, artist?:"Unknow", duration?: 0L, year?:0, album?:"Unknow", uri = uri)
    } ?: emptyList()
}