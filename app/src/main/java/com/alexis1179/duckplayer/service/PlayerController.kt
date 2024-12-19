package com.alexis1179.duckplayer.service

import android.app.Application
import androidx.media3.exoplayer.ExoPlayer

class PlayerController:Application (

){
    private var player: ExoPlayer? = null
    fun getPlayer(): ExoPlayer? = player
    fun setPlayer(player: ExoPlayer) {
        this.player = player
    }

    companion object {
        @JvmStatic
        private var instance : PlayerController? = null
        fun getInstance(): PlayerController {
            if(instance == null)
                instance = PlayerController()
            return instance!!

        }

    }
}