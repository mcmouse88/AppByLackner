package com.mcmouse88.note_app.feature_note.presentation.util

sealed class Screens(val route: String) {
    data object NotesScreen : Screens("notes_screen")
    data object AddEditNotesScreen : Screens("add_edit_notes_screen")
}
