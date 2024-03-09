package com.mcmouse88.record_and_play_audio.record

import java.io.File

interface AudioRecorder {
    fun start(outputFile: File)
    fun stop()
}