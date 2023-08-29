package com.mcmouse88.note_app.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.mcmouse88.note_app.ui.theme.LightGray

@Composable
fun CustomSnackbar(
    snackbarHostState: SnackbarHostState,
    containerColor: Color = LightGray,
    actionColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.primary
) {
    SnackbarHost(hostState = snackbarHostState) {
        Snackbar(
            snackbarData = it,
            containerColor = containerColor,
            actionColor = actionColor,
            contentColor = contentColor
        )
    }
}