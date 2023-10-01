package com.mcmouse88.mvvm_news_app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.mcmouse88.mvvm_news_app.R
import com.mcmouse88.mvvm_news_app.data.local.ArticleDatabase
import com.mcmouse88.mvvm_news_app.databinding.ActivityNewsBinding
import com.mcmouse88.mvvm_news_app.repository.NewsRepository

class NewsActivity : AppCompatActivity() {

    private var _binding: ActivityNewsBinding? = null
    private val binding: ActivityNewsBinding
        get() = _binding ?: throw NullPointerException("ActivityNewsBinding is null")

    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val repository = NewsRepository(ArticleDatabase.invoke(this))
        val factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]

        binding.bottomNavigationView.setupWithNavController(
            binding.newsNavHostFragment.findNavController()
        )
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}