package com.mcmouse88.mvvm_news_app.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcmouse88.mvvm_news_app.data.remote.dto.NewsResponseDto
import com.mcmouse88.mvvm_news_app.data.repository.NewsRepository
import com.mcmouse88.mvvm_news_app.ui.models.Article
import com.mcmouse88.mvvm_news_app.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val breakingNews = MutableLiveData<Resource<NewsResponseDto>>()
    var breakingNewsPage = 1
    var breakingNewsResponse: NewsResponseDto? = null

    val searchNews = MutableLiveData<Resource<NewsResponseDto>>()
    var searchNewsPage = 1
    var searchNewsResponse: NewsResponseDto? = null

    init {
        getBreakingNews("us")
    }

    fun getBreakingNews(countryCode: String) {
        viewModelScope.launch {
            breakingNews.postValue(Resource.Loading())
            val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
            breakingNews.postValue(handleBreakingNewsResponse(response))
        }
    }

    fun searchNews(searchQuery: String) {
        viewModelScope.launch {
            searchNews.postValue(Resource.Loading())
            val response = newsRepository.searchNews(searchQuery, searchNewsPage)
            searchNews.postValue(handleSearchNewsResponse(response))
        }
    }

    fun saveArticle(article: Article) {
        viewModelScope.launch {
            newsRepository.upsert(article)
        }
    }

    fun getSavedNews() = newsRepository.getSavedNews()

    fun deleteArticle(article: Article) {
        viewModelScope.launch {
            newsRepository.deleteArticle(article)
        }
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponseDto>): Resource<NewsResponseDto> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                breakingNewsPage++
                if (breakingNewsResponse == null) {
                    breakingNewsResponse = result
                } else {
                    val oldArticles = breakingNewsResponse?.articles
                    val newArticles = result.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(breakingNewsResponse ?: result)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponseDto>): Resource<NewsResponseDto> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                searchNewsPage++
                if (searchNewsResponse == null) {
                    searchNewsResponse = result
                } else {
                    val oldArticles = searchNewsResponse?.articles
                    val newArticles = result.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(searchNewsResponse ?: result)
            }
        }
        return Resource.Error(response.message())
    }
}