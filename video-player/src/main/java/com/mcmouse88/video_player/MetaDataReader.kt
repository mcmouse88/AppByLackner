package com.mcmouse88.video_player

import android.content.Context
import android.net.Uri
import android.provider.MediaStore

interface MetaDataReader {
    fun getMetaDataFromUri(contentUri: Uri): MetaData?
}

class MetaDataReaderImpl(
    private val context: Context
) : MetaDataReader {

    override fun getMetaDataFromUri(contentUri: Uri): MetaData? {
        if (contentUri.scheme != "content") return null
        val filename = context.contentResolver.query(
            contentUri,
            arrayOf(MediaStore.Video.VideoColumns.DISPLAY_NAME),
            null,
            null,
            null
        )?.use { cursor ->
            val index = cursor.getColumnIndex(MediaStore.Video.VideoColumns.DISPLAY_NAME)
            cursor.moveToFirst()
            cursor.getString(index)
        }
        return filename?.let{ fileName ->
            MetaData(
                filename = Uri.parse(fileName).lastPathSegment ?: return null
            )
        }
    }
}

data class MetaData(
    val filename: String
)
