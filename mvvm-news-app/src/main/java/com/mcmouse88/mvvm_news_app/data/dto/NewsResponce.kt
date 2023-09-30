package com.mcmouse88.mvvm_news_app.data.dto

data class NewsResponce(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)