package com.mcmouse88.testing_on_android.di

import android.content.Context
import androidx.room.Room
import com.mcmouse88.testing_on_android.data.local.ShoppingItemDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@[Module InstallIn(SingletonComponent::class)]
object TestAppModule {

    @[Provides ShoppingItemDbTest]
    fun provideInMemoryDb(
        @ApplicationContext context: Context
    ): ShoppingItemDatabase {
        return Room.inMemoryDatabaseBuilder(context, ShoppingItemDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }
}

@[Qualifier Retention(AnnotationRetention.RUNTIME)]
annotation class ShoppingItemDbTest