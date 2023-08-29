package com.mcmouse88.note_app.feature_note.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mcmouse88.note_app.feature_note.domain.model.Note

@Entity(tableName = "Note")
data class NoteEntity(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int
)

fun NoteEntity.toNote(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        timestamp = timestamp,
        color = color
    )
}

fun List<NoteEntity>.toListNote(): List<Note> {
    return this.map { it.toNote() }
}

fun Note.toNoteEntity(): NoteEntity {
    return  NoteEntity(
        id = id,
        title = title,
        content = content,
        timestamp = timestamp,
        color = color
    )
}