package com.mcmouse88.mvvm_news_app.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import androidx.core.os.bundleOf
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mcmouse88.mvvm_news_app.R
import com.mcmouse88.mvvm_news_app.databinding.FragmentSearchNewsBinding
import com.mcmouse88.mvvm_news_app.ui.NewsActivity
import com.mcmouse88.mvvm_news_app.ui.NewsViewModel
import com.mcmouse88.mvvm_news_app.ui.adapters.NewsAdapter
import com.mcmouse88.mvvm_news_app.ui.models.fromDtoToArticleList
import com.mcmouse88.mvvm_news_app.utils.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val SEARCH_NEWS_TIME_DELAY = 500L

class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {

    private var _binding: FragmentSearchNewsBinding? = null
    private val binding: FragmentSearchNewsBinding
        get() = _binding ?: throw NullPointerException("FragmentSearchNewsBinding is null")

    private val TAG = "SearchNewsFragment"

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchNewsBinding.bind(view)
        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()

        newsAdapter.setOnItemClickListener { article ->
            val bundle = bundleOf("article" to article)
            findNavController().navigate(
                R.id.action_searchNewsFragment_to_articleFragment,
                bundle
            )
        }

        var job: Job? = null
        binding.etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                editable?.let {
                    if (editable.toString().isNotBlank()) {
                        viewModel.searchNews(editable.toString())
                    }
                }
            }
        }

        viewModel.searchNews.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    hideProgressBar()
                    resource.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles.fromDtoToArticleList())
                        val totalPages = newsResponse.totalResults / QUERY_PAGE_SIZE + 2
                        isLastPage = viewModel.searchNewsPage == totalPages
                        if (isLastPage) {
                            binding.rvSearchNews.setPadding(0, 0, 0, 0)
                        }
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    resource.message?.let { message ->
                        Log.e(TAG, "An error occurred: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.isVisible = true
        isLoading = true
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.isInvisible = true
        isLoading = false
    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    private val scrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = isLoading.not() && isLastPage.not()
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning
                    && isTotalMoreThanVisible && isScrolling

            if (shouldPaginate) {
                viewModel.searchNews(binding.etSearch.text.toString())
                isScrolling = false
            }
        }
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(scrollListener)
        }
    }
}