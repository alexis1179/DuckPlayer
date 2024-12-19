package com.alexis1179.duckplayer.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.alexis1179.duckplayer.service.PlayerController

@Composable
fun SongListItem(name: String, artist: String, duration: String, uri: String) {
    val playerController = PlayerController.getInstance()
    if(playerController.getPlayer() == null)
        playerController.setPlayer(ExoPlayer.Builder(LocalContext.current).build())
    val player = playerController.getPlayer()!!
    Button(modifier = Modifier
        .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        shape = RectangleShape,
        contentPadding = PaddingValues(6.dp, 4.dp),
        onClick = {
            player.setMediaItem(MediaItem.fromUri(uri))
            player.prepare()
            player.play()

        }) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 5.dp, horizontal = 0.dp)
        ) {
            Text(
                text = name.replace(Regex("([.]\\w+)"), ""),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(MaterialTheme.colorScheme.onSurfaceVariant)
            )
            Text(
                text = artist,
                style = TextStyle(MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 13.sp)
            )
        }
        Button(
            onClick = {},
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Text(text = "Op", style = TextStyle(MaterialTheme.colorScheme.onSurfaceVariant))
        }
    }

}