package com.mcmouse88.sharing_data_between_screens

import android.content.Context
import android.content.SharedPreferences
import com.mcmouse88.sharing_data_between_screens.content.SessionCache
import com.mcmouse88.sharing_data_between_screens.content.SessionCacheImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object AppModule {

    @[Provides Singleton]
    fun providesSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences("session_prefs", Context.MODE_PRIVATE)
    }

    @[Provides Singleton]
    fun providesSessionCache(
        prefs: SharedPreferences
    ): SessionCache = SessionCacheImpl(prefs)
}