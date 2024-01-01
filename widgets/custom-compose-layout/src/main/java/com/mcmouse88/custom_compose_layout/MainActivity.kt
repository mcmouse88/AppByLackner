package com.mcmouse88.custom_compose_layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.dp
import com.mcmouse88.custom_compose_layout.ui.theme.CustomComposeLayoutTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CustomComposeLayoutTheme {
                FlowRow {
                    repeat(20) {
                        Box(
                            modifier = Modifier
                                .width(Random.nextInt(50, 200).dp)
                                .height(100.dp)
                                .background(Color(Random.nextLong(0xFFFFFFFF)))
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FlowRow(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content,
        measurePolicy = { measurables, constraints ->
            val placeables = measurables.map { measurable ->
                measurable.measure(constraints)
            }

            val groupedPlaceables = mutableListOf<List<Placeable>>()
            var currentGroup = mutableListOf<Placeable>()
            var currentGroupWidth = 0

            placeables.forEach { placeable ->
                if (currentGroupWidth + placeable.width <= constraints.maxWidth) {
                    currentGroup.add(placeable)
                    currentGroupWidth += placeable.width
                } else {
                    groupedPlaceables.add(currentGroup)
                    currentGroup = mutableListOf(placeable)
                    currentGroupWidth = placeable.width
                }
            }

            if (currentGroup.isNotEmpty()) {
                groupedPlaceables.add(currentGroup)
            }

            layout(
                width = constraints.maxWidth,
                height = constraints.maxHeight
            ) {
                var yPos = 0
                groupedPlaceables.forEach { row ->
                    var xPos = 0
                    row.forEach { placeable ->
                        placeable.place(
                            x = xPos,
                            y = yPos
                        )
                        xPos += placeable.width
                    }
                    yPos += row.maxOfOrNull { it.height } ?: 0
                }
            }
        }
    )
}