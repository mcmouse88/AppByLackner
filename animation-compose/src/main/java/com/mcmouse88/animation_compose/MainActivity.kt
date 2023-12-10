package com.mcmouse88.animation_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mcmouse88.animation_compose.ui.theme.AnimationComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            AnimationComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        var isVisible by remember {
                            mutableStateOf(false)
                        }

                        var isRound by remember {
                            mutableStateOf(false)
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Button(
                                onClick = {
                                    isVisible = !isVisible
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(text = stringResource(R.string.transition))
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                onClick = {
                                    isRound = !isRound
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(text = stringResource(R.string.round))
                            }
                        }

                        val transition = updateTransition(
                            targetState = isRound,
                            label = "transition"
                        )
                        val borderRadius by animateIntAsState(
                            targetValue = if (isRound) 100 else 0,
                            animationSpec = tween(
                                durationMillis = 1000,
                                delayMillis = 500
                            ),
                            label = "round"
                        )

                        val frameRadius by transition.animateInt(
                            transitionSpec = { tween(2_000) },
                            label = "frameRadius",
                            targetValueByState = { isRounded ->
                                if (isRounded) 100 else 0
                            }
                        )

                        val color by transition.animateColor(
                            transitionSpec = { tween(1_000) },
                            label = "Color",
                            targetValueByState = { isRounded ->
                                if (isRounded) Color.Green else Color.Red
                            }
                        )

                        val repeatable = rememberInfiniteTransition(label = "infinite")
                        val newColor by repeatable.animateColor(
                            initialValue = Color.Red,
                            animationSpec = infiniteRepeatable(
                                animation = tween(2_000),
                                repeatMode = RepeatMode.Reverse
                            ),
                            targetValue = Color.Green,
                            label = "repeatable"
                        )

                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(borderRadius))
                                .background(Color.Red)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(frameRadius))
                                .background(color = color)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .background(color = newColor)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        AnimatedContent(
                            targetState = isVisible,
                            modifier = Modifier.size(100.dp),
                            label = "animated content",
                            transitionSpec = {
                                // fadeIn() togetherWith fadeOut()
                                slideInHorizontally(
                                    initialOffsetX = { offset ->
                                        if (isVisible) offset else -offset
                                    }
                                ) togetherWith slideOutHorizontally(
                                    targetOffsetX = { offset ->
                                        if (isVisible) -offset else offset
                                    }
                                )
                            }
                        ) { visible ->
                            if (visible) {
                                Box(modifier = Modifier.background(Color.Green))
                            } else {
                                Box(modifier = Modifier.background(Color.Red))
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        AnimatedVisibility(
                            visible = isVisible,
                            enter = slideInHorizontally() + fadeIn(),
                            modifier = Modifier
                                .size(100.dp)
                        ) {
                            Box(modifier = Modifier.background(Color.Red))
                        }
                    }
                }
            }
        }
    }
}