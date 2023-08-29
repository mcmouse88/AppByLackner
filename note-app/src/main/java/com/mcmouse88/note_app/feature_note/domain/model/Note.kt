package com.mcmouse88.note_app.feature_note.domain.model

import com.mcmouse88.note_app.ui.theme.BabyBlue
import com.mcmouse88.note_app.ui.theme.LightGreen
import com.mcmouse88.note_app.ui.theme.RedOrange
import com.mcmouse88.note_app.ui.theme.RedPink
import com.mcmouse88.note_app.ui.theme.Violet

data class Note(
    val id: Int? = null,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String) : Exception(message)