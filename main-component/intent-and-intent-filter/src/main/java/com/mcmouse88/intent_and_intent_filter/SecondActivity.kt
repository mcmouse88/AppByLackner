package com.mcmouse88.intent_and_intent_filter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.mcmouse88.intent_and_intent_filter.ui.theme.IntentAndIntentFilterTheme

class SecondActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntentAndIntentFilterTheme {
                Text(text = "Second Activity")
            }
        }
    }
}