package com.mcmouse88.stock_market_app.di

import android.content.Context
import androidx.room.Room
import com.mcmouse88.stock_market_app.BuildConfig
import com.mcmouse88.stock_market_app.data.local.StockDao
import com.mcmouse88.stock_market_app.data.local.StockDatabase
import com.mcmouse88.stock_market_app.data.remote.StockApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object AppModule {

    @[Provides Singleton]
    fun provideStockApi(): StockApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @[Provides Singleton]
    fun provideStockDatabase(
        @ApplicationContext context: Context
    ): StockDatabase {
        return Room.databaseBuilder(
            context,
            StockDatabase::class.java,
            "stock_db.db"
        ).build()
    }

    @[Provides Singleton]
    fun providesStockDao(database: StockDatabase): StockDao {
        return database.dao
    }
}