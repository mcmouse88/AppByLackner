package com.mcmouse88.mvvm_news_app.ui

import androidx.lifecycle.ViewModel
import com.mcmouse88.mvvm_news_app.repository.NewsRepository

class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {
}