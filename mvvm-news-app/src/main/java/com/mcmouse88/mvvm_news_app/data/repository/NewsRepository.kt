package com.mcmouse88.mvvm_news_app.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.mcmouse88.mvvm_news_app.data.local.ArticleDatabase
import com.mcmouse88.mvvm_news_app.data.remote.RetrofitInstance
import com.mcmouse88.mvvm_news_app.data.remote.dto.NewsResponseDto
import com.mcmouse88.mvvm_news_app.ui.models.Article
import com.mcmouse88.mvvm_news_app.ui.models.toArticleList
import com.mcmouse88.mvvm_news_app.ui.models.toEntity
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

    suspend fun upsert(article: Article): Long {
        return database.articleDao.upsert(article.toEntity())
    }

    fun getSavedNews(): LiveData<List<Article>> {
        return database.articleDao.getAllArticles().map {
            it.toArticleList()
        }
    }

    suspend fun deleteArticle(article: Article) {
        database.articleDao.deleteArticle(article.toEntity())
    }
}
