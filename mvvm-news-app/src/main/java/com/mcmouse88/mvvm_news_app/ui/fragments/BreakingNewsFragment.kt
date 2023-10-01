package com.mcmouse88.mvvm_news_app.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mcmouse88.mvvm_news_app.R
import com.mcmouse88.mvvm_news_app.databinding.FragmentBreakingNewsBinding
import com.mcmouse88.mvvm_news_app.ui.NewsActivity
import com.mcmouse88.mvvm_news_app.ui.NewsViewModel
import com.mcmouse88.mvvm_news_app.ui.adapters.NewsAdapter
import com.mcmouse88.mvvm_news_app.ui.models.toArticleList
import com.mcmouse88.mvvm_news_app.utils.Resource

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    private var _binding: FragmentBreakingNewsBinding? = null
    val binding: FragmentBreakingNewsBinding
        get() = _binding ?: throw NullPointerException("FragmentBreakingNewsBinding is null")

    private val TAG = "BreakingNewsFragment"

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()

        newsAdapter.setOnItemClickListener { article ->  
            val bundle = bundleOf("article" to article)
            findNavController().navigate(
                R.id.action_breakingNewsFragment_to_articleFragment,
                bundle
            )
        }

        viewModel.breakingNews.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    binding.paginationProgressBar.isInvisible = true
                    resource.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles.toArticleList())
                    }
                }
                is Resource.Error -> {
                    binding.paginationProgressBar.isInvisible = true
                    resource.message?.let { message ->
                        Log.e(TAG, "An error occurred: $message")
                    }
                }
                is Resource.Loading -> {
                    binding.paginationProgressBar.isVisible = true
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}