package com.mcmouse88.intent_and_intent_filter

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ImageViewModel : ViewModel() {

    var uri: Uri? by mutableStateOf(null)
        private set

    fun updateUri(uri: Uri?) {
        this.uri = uri
    }
}