package com.mcmouse88.testing_on_android.di

import android.content.Context
import androidx.room.Room
import com.mcmouse88.testing_on_android.data.local.ShoppingDao
import com.mcmouse88.testing_on_android.data.local.ShoppingItemDatabase
import com.mcmouse88.testing_on_android.data.remote.PixabayApi
import com.mcmouse88.testing_on_android.others.Constants.BASE_URL
import com.mcmouse88.testing_on_android.others.Constants.DATABASE_NAME
import com.mcmouse88.testing_on_android.repository.DefaultShoppingRepository
import com.mcmouse88.testing_on_android.repository.ShoppingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
class AppModule {

    @[Singleton Provides]
    fun provideShoppingItemDatabase(
        @ApplicationContext context: Context
    ): ShoppingItemDatabase {
        return Room.databaseBuilder(context, ShoppingItemDatabase::class.java, DATABASE_NAME).build()
    }

    @[Singleton Provides]
    fun provideShoppingDao(
        database: ShoppingItemDatabase
    ): ShoppingDao = database.getShoppingDao()

    @[Singleton Provides]
    fun providePixabayApi(): PixabayApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create()
    }

    @[Singleton Provides]
    fun provideDefaultShoppingRepository(
        defaultRepository: DefaultShoppingRepository
    ): ShoppingRepository = defaultRepository
}