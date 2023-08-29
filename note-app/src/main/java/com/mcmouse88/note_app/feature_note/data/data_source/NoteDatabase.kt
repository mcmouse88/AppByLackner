package com.mcmouse88.note_app.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mcmouse88.note_app.feature_note.data.models.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}