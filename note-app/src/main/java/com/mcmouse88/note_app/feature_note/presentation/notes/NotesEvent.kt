package com.mcmouse88.note_app.feature_note.presentation.notes

import com.mcmouse88.note_app.feature_note.domain.model.Note
import com.mcmouse88.note_app.feature_note.domain.util.NoteOrder

sealed class NotesEvent {

    data class Order(
        val noteOrder: NoteOrder
    ) : NotesEvent()

    data class DeleteNote(
        val note: Note
    ) : NotesEvent()

    data object RestoreNote : NotesEvent()
    data object ToggleOrderSection : NotesEvent()
}
