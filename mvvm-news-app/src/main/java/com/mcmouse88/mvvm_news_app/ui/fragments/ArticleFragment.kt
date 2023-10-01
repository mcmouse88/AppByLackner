package com.mcmouse88.mvvm_news_app.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.mcmouse88.mvvm_news_app.R
import com.mcmouse88.mvvm_news_app.databinding.FragmentArticleBinding
import com.mcmouse88.mvvm_news_app.ui.NewsActivity
import com.mcmouse88.mvvm_news_app.ui.NewsViewModel

class ArticleFragment : Fragment(R.layout.fragment_article) {

    private var _binding: FragmentArticleBinding? = null
    val binding: FragmentArticleBinding
        get() = _binding ?: throw NullPointerException("FragmentArticleBinding is null")

    lateinit var viewModel: NewsViewModel
    val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentArticleBinding.bind(view)
        viewModel = (activity as NewsActivity).viewModel
        val article = args.article
        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }

        binding.fab.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}