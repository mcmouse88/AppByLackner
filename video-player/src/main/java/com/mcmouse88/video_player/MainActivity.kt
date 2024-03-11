package com.mcmouse88.video_player

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.ui.PlayerView
import com.mcmouse88.video_player.ui.theme.VideoPlayerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VideoPlayerTheme {
                val viewModel = hiltViewModel<MainViewModel>()
                val videoItems by viewModel.videoItems.collectAsStateWithLifecycle()
                val selectVideoLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent(),
                    onResult = { uri ->
                        uri?.let(viewModel::addVideoUri)
                    }
                )

                var lifecycle by remember {
                    mutableStateOf(Lifecycle.Event.ON_CREATE)
                }

                val lifecycleOwner = LocalLifecycleOwner.current
                DisposableEffect(key1 = lifecycleOwner) {
                    val observer = LifecycleEventObserver { _, event ->
                        lifecycle = event
                    }

                    lifecycleOwner.lifecycle.addObserver(observer)
                    onDispose {
                        lifecycleOwner.lifecycle.removeObserver(observer)
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    AndroidView(
                        factory = { context ->
                            PlayerView(context).also {
                                it.player = viewModel.player
                            }
                        },
                        update = {
                            when (lifecycle) {
                                Lifecycle.Event.ON_RESUME -> {
                                    it.onResume()
                                }

                                Lifecycle.Event.ON_PAUSE -> {
                                    it.onPause()
                                    it.player?.pause()
                                }

                                else -> Unit
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16 / 9f)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    IconButton(
                        onClick = {
                            selectVideoLauncher.launch("video/mp4")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.FileOpen,
                            contentDescription = "Select video"
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    LazyColumn(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(videoItems) { item ->
                            Text(
                                text = item.name,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        viewModel.playVideo(item.contentUri)
                                    }
                                    .padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}