package com.mcmouse88.sharing_data_between_screens.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

val LocalSnackbarHostState = compositionLocalOf {
    SnackbarHostState()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppRoot() {
    val snackbarHostState = LocalSnackbarHostState.current
    CompositionLocalProvider(LocalSnackbarHostState provides snackbarHostState) {
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = LocalSnackbarHostState.current)
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                MyScreen()
            }
        }
    }
}

@Composable
fun MyScreen() {
    val snackbarHostState = LocalSnackbarHostState.current
    val scope = rememberCoroutineScope()
    Button(onClick = {
        scope.launch {
            snackbarHostState.showSnackbar("Hello World!")
        }
    }) {
        Text(text = "Show snackbar")
    }
}