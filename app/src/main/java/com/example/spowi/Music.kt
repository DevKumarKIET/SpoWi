package com.example.spowi

import android.media.MediaMetadataRetriever
import java.util.concurrent.TimeUnit

//Data Class song ka naam , duration singer, album in sbb k liye....

data class Music(
    val id: String,
    val title: String,
    val album: String,
    val artist: String,
    val duration: Long = 0,
    val path: String,
    val artUri: String
)

fun formatDuration(duration: Long): String {
    val minutes = TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS)
    val seconds = (TimeUnit.SECONDS.convert(
        duration,
        TimeUnit.MILLISECONDS
    ) - minutes * TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES))
    return String.format("%02d:%02d", minutes, seconds)
}

fun getImgArt(path:String): ByteArray? {
    val retriever = MediaMetadataRetriever()
    retriever.setDataSource(path)
    return retriever.embeddedPicture
}

fun setSongPositionPA(increment: Boolean) {
    if (increment) {
        if (PlayerActivity.musicListPA.size - 1 == PlayerActivity.songPosition)
            PlayerActivity.songPosition = 0
        else
            ++PlayerActivity.songPosition
    } else {
        if (0 == PlayerActivity.songPosition)
            PlayerActivity.songPosition = PlayerActivity.musicListPA.size - 1
        else
            --PlayerActivity.songPosition
    }
}
