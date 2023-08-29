package com.mcmouse88.note_app.feature_note.data.repository

import com.mcmouse88.note_app.feature_note.data.data_source.NoteDao
import com.mcmouse88.note_app.feature_note.data.models.toListNote
import com.mcmouse88.note_app.feature_note.data.models.toNote
import com.mcmouse88.note_app.feature_note.data.models.toNoteEntity
import com.mcmouse88.note_app.feature_note.domain.model.Note
import com.mcmouse88.note_app.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao
) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes().map { it.toListNote() }
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)?.toNote()
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note.toNoteEntity())
    }

    override suspend fun deleteNode(note: Note) {
        dao.deleteNote(note.toNoteEntity())
    }
}