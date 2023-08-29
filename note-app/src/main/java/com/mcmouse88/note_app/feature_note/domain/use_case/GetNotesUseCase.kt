package com.mcmouse88.note_app.feature_note.domain.use_case

import com.mcmouse88.note_app.feature_note.domain.model.Note
import com.mcmouse88.note_app.feature_note.domain.repository.NoteRepository
import com.mcmouse88.note_app.feature_note.domain.util.NoteOrder
import com.mcmouse88.note_app.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val repository: NoteRepository
) {
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>> {
        return repository.getNotes().map { notes ->
            when(noteOrder.orderType) {
                OrderType.Ascending -> {
                    when (noteOrder) {
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedBy(Note::timestamp)
                        is NoteOrder.Color -> notes.sortedBy(Note::color)
                    }
                }
                OrderType.Descending -> {
                    when (noteOrder) {
                        is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedByDescending(Note::timestamp)
                        is NoteOrder.Color -> notes.sortedByDescending(Note::color)
                    }
                }
            }
        }
    }
}