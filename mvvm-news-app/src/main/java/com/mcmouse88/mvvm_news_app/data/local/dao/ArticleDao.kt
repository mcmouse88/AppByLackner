package com.mcmouse88.mvvm_news_app.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.mcmouse88.mvvm_news_app.data.local.entities.ArticleEntity

@Dao
interface ArticleDao {

    @Upsert
    suspend fun upsert(article: ArticleEntity): Long

    @Query("SELECT * FROM articles_table")
    fun getAllArticles(): LiveData<List<ArticleEntity>>

    @Delete
    suspend fun deleteArticle(article: ArticleEntity)
}