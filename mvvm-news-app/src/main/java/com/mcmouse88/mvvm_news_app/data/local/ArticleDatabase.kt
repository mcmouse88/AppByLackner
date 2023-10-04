package com.mcmouse88.mvvm_news_app.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mcmouse88.mvvm_news_app.data.local.converters.Converters
import com.mcmouse88.mvvm_news_app.data.local.dao.ArticleDao
import com.mcmouse88.mvvm_news_app.data.local.entities.ArticleEntity

@Database(
    entities = [ArticleEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {
    abstract val articleDao: ArticleDao

    companion object {
        @Volatile
        private var INSTANCE: ArticleDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: createDataBase(context).also { INSTANCE = it }
        }

        private fun createDataBase(context: Context): ArticleDatabase {
            return  Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "articles.db"
            ).build()
        }
    }
}