package com.mcmouse88.mvvm_news_app.data.remote.dto

data class NewsResponseDto(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)