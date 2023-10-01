package com.mcmouse88.mvvm_news_app.data.repository

import com.mcmouse88.mvvm_news_app.data.local.ArticleDatabase
import com.mcmouse88.mvvm_news_app.data.remote.RetrofitInstance
import com.mcmouse88.mvvm_news_app.data.remote.dto.NewsResponseDto
import retrofit2.Response

class NewsRepository(
    private val database: ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int): Response<NewsResponseDto> {
        return RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)
    }

    suspend fun searchNews(searchQuery: String, pageNumber: Int): Response<NewsResponseDto> {
        return RetrofitInstance.api.searchForNews(searchQuery, pageNumber)
    }
}
