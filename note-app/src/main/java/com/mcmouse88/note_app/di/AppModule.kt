package com.mcmouse88.note_app.di

import android.content.Context
import androidx.room.Room
import com.mcmouse88.note_app.feature_note.data.data_source.NoteDao
import com.mcmouse88.note_app.feature_note.data.data_source.NoteDatabase
import com.mcmouse88.note_app.feature_note.data.repository.NoteRepositoryImpl
import com.mcmouse88.note_app.feature_note.domain.repository.NoteRepository
import com.mcmouse88.note_app.feature_note.domain.use_case.AddNoteUseCase
import com.mcmouse88.note_app.feature_note.domain.use_case.DeleteNoteUseCase
import com.mcmouse88.note_app.feature_note.domain.use_case.GetNoteByIdUseCase
import com.mcmouse88.note_app.feature_note.domain.use_case.GetNotesUseCase
import com.mcmouse88.note_app.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object AppModule {

    @[Provides Singleton]
    fun provideNoteDatabase(
        @ApplicationContext appContext: Context
    ): NoteDatabase {
        return Room.databaseBuilder(
            appContext,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
            ).build()
    }

    @[Provides Singleton]
    fun provideNoteDao(
        db: NoteDatabase
    ): NoteDao = db.noteDao

    @[Provides Singleton]
    fun provideNoteRepository(
        impl: NoteRepositoryImpl
    ): NoteRepository {
        return impl
    }

    @[Provides Singleton]
    fun provideNoteUseCases(
        repository: NoteRepository
    ): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotesUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            addNote = AddNoteUseCase(repository),
            getNoteById = GetNoteByIdUseCase(repository)
        )
    }
}