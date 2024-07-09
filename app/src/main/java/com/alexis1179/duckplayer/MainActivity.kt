package com.alexis1179.duckplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.alexis1179.duckplayer.ui.theme.DuckPlayerTheme
import com.alexis1179.duckplayer.view.components.CustomTopBar
import com.alexis1179.duckplayer.view.utils.NavAppHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DuckPlayerTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {CustomTopBar()}) { innerPadding ->
                    NavAppHost(
                        navController = rememberNavController(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}