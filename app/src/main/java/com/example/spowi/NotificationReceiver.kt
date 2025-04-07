package com.example.spowi

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlin.system.exitProcess

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            ApplicationClass.PREVIOUS -> {
                prevNextSong(increment = false , context = context!!)
            }

            ApplicationClass.PLAY ->
                if (PlayerActivity.isPlaying) pauseMusic()
                else playMusic()

            ApplicationClass.NEXT -> {
                prevNextSong(increment = true , context = context!!)
            }

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

    private fun prevNextSong(increment:Boolean , context:Context){
        setSongPositionPA(increment = increment)
        PlayerActivity.musicServicePA!!.createMediaPlayer()
        Glide.with(context)
            .load(PlayerActivity.musicListPA[PlayerActivity.songPosition].artUri)
            .apply(RequestOptions().placeholder(R.drawable.splash_screen).centerCrop())
            .into(PlayerActivity.binding.songImgPA)
        PlayerActivity.binding.songNamePA.text = PlayerActivity.musicListPA[PlayerActivity.songPosition].title
        playMusic()
    }

}
















