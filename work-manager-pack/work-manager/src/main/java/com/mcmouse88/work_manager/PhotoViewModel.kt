package com.mcmouse88.work_manager

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.UUID

class PhotoViewModel : ViewModel() {

    var uncompressUri by mutableStateOf<Uri?>(null)
        private set

    var compressedBitmap by mutableStateOf<Bitmap?>(null)
        private set

    var workId by mutableStateOf<UUID?>(null)
        private set

    fun updateUncompressUri(uri: Uri?) {
        uncompressUri = uri
    }

    fun updateCompressBitmap(bitmap: Bitmap?) {
        compressedBitmap = bitmap
    }

    fun updateWorkId(uuid: UUID?) {
        workId = uuid
    }
}