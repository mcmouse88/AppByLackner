package com.mcmouse88.date_time_api_guide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mcmouse88.date_time_api_guide.ui.theme.DateTimeApiTheme
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DateTimeApiTheme {
                val date = remember {
                    ZonedDateTime.now()

                    // Get date from timestamp
                    /*ZonedDateTime.ofInstant(
                        Instant.ofEpochMilli(System.currentTimeMillis()),
                        ZoneId.systemDefault()
                    )*/
                }

                val formattedDateTime = remember {
                    DateTimeFormatter.ofPattern("EEE dd MM yyyy HH:mm:ss").format(date)
                }

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = formattedDateTime)
                }
            }
        }
    }
}