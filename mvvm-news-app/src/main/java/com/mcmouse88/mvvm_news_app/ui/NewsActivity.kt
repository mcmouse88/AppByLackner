package com.mcmouse88.mvvm_news_app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.mcmouse88.mvvm_news_app.R
import com.mcmouse88.mvvm_news_app.data.local.ArticleDatabase
import com.mcmouse88.mvvm_news_app.data.repository.NewsRepository
import com.mcmouse88.mvvm_news_app.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {

    private var _binding: ActivityNewsBinding? = null
    private val binding: ActivityNewsBinding
        get() = _binding ?: throw NullPointerException("ActivityNewsBinding is null")

    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNewsBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        val repository = NewsRepository(ArticleDatabase.invoke(this))
        val factory = ViewModelFactory(application, repository)
        viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]

        val navHost = supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(
            navHost.navController
        )
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}