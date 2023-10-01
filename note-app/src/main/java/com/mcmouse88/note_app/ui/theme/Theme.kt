package com.mcmouse88.note_app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color.White,
    background = DarkGray,
    onBackground = Color.White,
    surface = LightBlue,
    onSurface = DarkGray
)

@Composable
fun NoteAppTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        shapes = Shapes,
        typography = Typography,
        content = content
    )
}