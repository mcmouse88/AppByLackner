package com.mcmouse88.mvvm_news_app.ui.models

import com.mcmouse88.mvvm_news_app.data.remote.dto.SourceDto

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: SourceDto,
    val title: String,
    val url: String,
    val urlToImage: String
)
