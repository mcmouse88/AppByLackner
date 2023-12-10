package com.mcmouse88.zoom_gestures_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import com.mcmouse88.zoom_gestures_compose.ui.theme.ZoomGesturesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZoomGesturesTheme {
                var scale by remember { mutableFloatStateOf(1f) }
                var rotation by remember { mutableFloatStateOf(1f) }
                var offset by remember { mutableStateOf(Offset.Zero) }

                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1024f / 573f)
                ) {
                    val state = rememberTransformableState { zoomChange, panChange, rotationChange ->
                        scale = (scale * zoomChange).coerceIn(minimumValue = 1f, maximumValue = 5f)
                        rotation += rotationChange

                        val extraWidth = (scale - 1) * constraints.maxWidth
                        val extraHeight = (scale -1) * constraints.maxHeight

                        val maxX = extraWidth / 2
                        val maxY = extraHeight / 2

                        offset = Offset(
                            x = (offset.x + scale * panChange.x).coerceIn(-maxX, maxX),
                            y = (offset.y + scale * panChange.y).coerceIn(-maxY, maxY)
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.kermit),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                                rotationZ = rotation
                                translationX = offset.x
                                translationY = offset.y
                            }
                            .transformable(state = state)
                    )
                }
            }
        }
    }
}