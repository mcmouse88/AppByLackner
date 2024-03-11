package com.mcmouse88.video_player

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

private const val SAVED_KEY = "videoUris"

@HiltViewModel
class MainViewModel @Inject constructor(
    val player: Player,
    private val savedStateHandle: SavedStateHandle,
    private val reader: MetaDataReader
) : ViewModel() {

    private val videoUris = savedStateHandle.getStateFlow(SAVED_KEY, emptyList<Uri>())

    val videoItems = videoUris.map { uris ->
        uris.map { uri ->
            VideoItem(
                contentUri = uri,
                mediaItem = MediaItem.fromUri(uri),
                name = reader.getMetaDataFromUri(uri)?.filename ?: "No Name"
            )
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    init {
        player.prepare()
    }

    fun addVideoUri(uri: Uri) {
        savedStateHandle[SAVED_KEY] = videoUris.value + uri
        player.addMediaItem(MediaItem.fromUri(uri))
    }

    fun playVideo(uri: Uri) {
        player.setMediaItem(
            videoItems.value.find { it.contentUri == uri }?.mediaItem ?: return
        )
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}