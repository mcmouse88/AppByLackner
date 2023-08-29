package com.mcmouse88.note_app.feature_note.domain.use_case

import com.mcmouse88.note_app.feature_note.domain.model.Note
import com.mcmouse88.note_app.feature_note.domain.repository.NoteRepository

class GetNoteByIdUseCase(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}