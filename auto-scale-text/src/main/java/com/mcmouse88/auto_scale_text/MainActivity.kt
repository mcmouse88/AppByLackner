package com.mcmouse88.auto_scale_text

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mcmouse88.auto_scale_text.ui.theme.AutoScaleTextTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutoScaleTextTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        Box(
                            modifier = Modifier
                                .width(100.dp)
                                .background(Color.LightGray)
                        ) {
                            Text(
                                text = "Hello World!",
                                style = MaterialTheme.typography.displayMedium,
                                softWrap = false
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .width(100.dp)
                                .background(Color.LightGray)
                        ) {
                            AutoResizeText(
                                text = "Hello World!",
                                style = MaterialTheme.typography.displayMedium
                            )
                        }
                    }
                }
            }
        }
    }
}