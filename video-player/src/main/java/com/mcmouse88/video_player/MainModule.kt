package com.mcmouse88.video_player

import android.content.Context
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@[Module InstallIn(ViewModelComponent::class)]
object MainModule {

    @[Provides ViewModelScoped]
    fun provideVideoPlayer(
        @ApplicationContext context: Context
    ): Player {
        return ExoPlayer.Builder(context).build()
    }

    @[Provides ViewModelScoped]
    fun provideMetaDataReader(
        @ApplicationContext context: Context
    ): MetaDataReader {
        return MetaDataReaderImpl(context)
    }
}