package com.mcmouse88.record_and_play_audio.playback

import java.io.File

interface AudioPlayer {
    fun playFile(file: File)
    fun stop()
}