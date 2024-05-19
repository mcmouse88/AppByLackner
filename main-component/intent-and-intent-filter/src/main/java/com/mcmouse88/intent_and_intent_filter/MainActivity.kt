package com.mcmouse88.intent_and_intent_filter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.mcmouse88.intent_and_intent_filter.ui.theme.IntentAndIntentFilterTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ImageViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntentAndIntentFilterTheme {
                Greeting(applicationContext, viewModel.uri)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(Intent.EXTRA_STREAM)
        }
        viewModel.updateUri(uri)
    }
}

@Composable
fun Greeting(packageContext: Context, uri: Uri?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        uri?.let {
            AsyncImage(model = uri, contentDescription = null)
        }
        Button(onClick = {
            /*Intent(packageContext, SecondActivity::class.java).also {
                packageContext.startActivity(it)
            }*/

            // TODO Need add to manifest <query> for using check handle intent
            /*Intent(Intent.ACTION_MAIN).also {
                it.`package` = "com.google.android.youtube"
                if (it.resolveActivity(packageContext.packageManager) != null) {
                    packageContext.startActivity(it)
                }
            }*/

            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("test@test.com"))
                putExtra(Intent.EXTRA_SUBJECT, "This is my subject")
                putExtra(Intent.EXTRA_TEXT, "This is the content of my email")
            }

            if (intent.resolveActivity(packageContext.packageManager) != null) {
                packageContext.startActivity(intent)
            }
        }) {
            Text(text = "Click me")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IntentAndIntentFilterTheme {
        Greeting(LocalContext.current, null)
    }
}