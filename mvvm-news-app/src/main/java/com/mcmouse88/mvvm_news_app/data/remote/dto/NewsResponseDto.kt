package com.mcmouse88.mvvm_news_app.data.remote.dto

data class NewsResponseDto(
    val articles: MutableList<ArticleDto>,
    val status: String,
    val totalResults: Int
)