package com.mcmouse88.mvvm_news_app.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcmouse88.mvvm_news_app.data.remote.dto.NewsResponseDto
import com.mcmouse88.mvvm_news_app.repository.NewsRepository
import com.mcmouse88.mvvm_news_app.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val breakingNews = MutableLiveData<Resource<NewsResponseDto>>()
    var breakingNewsPage = 1

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

    private fun handleBreakingNewsResponse(response: Response<NewsResponseDto>): Resource<NewsResponseDto> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}