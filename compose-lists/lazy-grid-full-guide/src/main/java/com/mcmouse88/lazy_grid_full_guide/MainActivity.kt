package com.mcmouse88.lazy_grid_full_guide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mcmouse88.lazy_grid_full_guide.ui.theme.LazyGridFullGuideTheme

class MainActivity : ComponentActivity() {

    /**
     * [GridCells.Adaptive] automatically organizes cells on the screen based on the screen size.
     * The minSize parameter specifies the minimum width for each cell. The cells are then evenly
     * distributed on the screen, ensuring that they all have the same width. This feature
     * is particularly useful when dealing with varying item counts and changing screen
     * orientations. It allows for dynamic adjustment of cell layout to accommodate different
     * screen sizes and orientations seamlessly.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyGridFullGuideTheme {

                val state = rememberLazyGridState(
                    initialFirstVisibleItemIndex = 99
                )

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // LazyVerticalGrid(columns = GridCells.Adaptive(100.dp)) {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(100.dp),
                        state = state,
                    ) {
                        items(100) { i->
                            Box(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .aspectRatio(1f)
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(Color.Green),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "Item $i")
                            }
                        }
                    }
                }
            }
        }
    }
}
