package com.mcmouse88.mvvm_news_app.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mcmouse88.mvvm_news_app.R
import com.mcmouse88.mvvm_news_app.ui.NewsActivity
import com.mcmouse88.mvvm_news_app.ui.NewsViewModel

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
    }
}