package com.mcmouse88.mvvm_news_app.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "articles_table"
)
data class ArticleEntity(
    @[PrimaryKey(autoGenerate = true) ColumnInfo("id")]
    val id: Int = 0,
    @ColumnInfo("author")
    val author: String,
    @ColumnInfo("content")
    val content: String,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("published_at")
    val publishedAt: String,
    val source: Source,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("url")
    val url: String,
    @ColumnInfo("url_to_image")
    val urlToImage: String
)

data class Source(
    val id: String,
    val name: String
)