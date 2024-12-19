package com.alexis1179.duckplayer.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PlayerScreen(){
    Column {
        Text(text = "Player Screen")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Play/Pause")
        }
    }
}