package com.alexis1179.duckplayer

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.compose.rememberNavController
import com.alexis1179.duckplayer.service.PlayerController
import com.alexis1179.duckplayer.ui.theme.DuckPlayerTheme
import com.alexis1179.duckplayer.utils.NavAppHost
import com.alexis1179.duckplayer.view.components.CustomTopBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var hasPermission by remember { mutableStateOf(false)}
            val permissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission()) {
                granted: Boolean ->
                 hasPermission = granted
            }
            LaunchedEffect(Unit) {
                hasPermission = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                if(!hasPermission)
                    permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }

            DuckPlayerTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = { CustomTopBar() }) { innerPadding ->
                    if (hasPermission) {
                        NavAppHost(
                            navController = rememberNavController(),
                            modifier = Modifier.padding(innerPadding)
                        )
                    } else {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(text = "Permiso no concedido, no puedes acceder al almacenamiento.")
                        }
                    }
                }
            }

            val playerController = PlayerController.getInstance()
            val exoPlayer = ExoPlayer.Builder(this).build()
            playerController.setPlayer(exoPlayer)
        }
    }
}