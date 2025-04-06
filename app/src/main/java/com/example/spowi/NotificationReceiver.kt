package com.example.spowi

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import kotlin.system.exitProcess

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            ApplicationClass.PREVIOUS -> {
                Toast.makeText(context, "Previous Clicked", Toast.LENGTH_SHORT).show()
                Log.d("NotificationReceiver", "Previous clicked")
            }

            ApplicationClass.PLAY ->
                if (PlayerActivity.isPlaying) pauseMusic()
                else playMusic()

            ApplicationClass.NEXT -> Toast.makeText(context, "Next Clicked", Toast.LENGTH_SHORT).show()

            ApplicationClass.EXIT -> {
                PlayerActivity.musicServicePA?.stopForeground(true)
                PlayerActivity.musicServicePA = null
                exitProcess(1)
            }
        }
    }

    private fun playMusic() {
        PlayerActivity.isPlaying = true
        PlayerActivity.musicServicePA!!.mediaPlayer!!.start()
        PlayerActivity.musicServicePA!!.showNotification(R.drawable.pause_icon)
        PlayerActivity.binding.playPauseBtnPA.setIconResource(R.drawable.pause_icon)
    }

    private fun pauseMusic() {
        PlayerActivity.isPlaying = false
        PlayerActivity.musicServicePA!!.mediaPlayer!!.pause()
        PlayerActivity.musicServicePA!!.showNotification(R.drawable.play_icon)
        PlayerActivity.binding.playPauseBtnPA.setIconResource(R.drawable.play_icon)
    }

}