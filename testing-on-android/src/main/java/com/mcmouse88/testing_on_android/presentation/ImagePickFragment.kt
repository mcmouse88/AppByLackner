package com.mcmouse88.testing_on_android.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mcmouse88.testing_on_android.R
import com.mcmouse88.testing_on_android.adapters.ImageAdapter
import com.mcmouse88.testing_on_android.databinding.FragmentImagePickBinding
import com.mcmouse88.testing_on_android.others.Constants.GRID_SPAN_COUNT
import javax.inject.Inject

class ImagePickFragment @Inject constructor(
    val imageAdapter: ImageAdapter
) : Fragment(R.layout.fragment_image_pick) {

    private var _binding: FragmentImagePickBinding? = null
    private val binding: FragmentImagePickBinding
        get() = _binding ?: throw IllegalStateException("FragmentImagePickBinding is null")

    lateinit var viewModel: ShoppingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentImagePickBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity())[ShoppingViewModel::class.java]
        setupRecyclerView()
        imageAdapter.setOnItemClickListener {
            findNavController().navigateUp()
            viewModel.setCurrentImageUrl(it)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setupRecyclerView() {
        binding.rvImages.apply {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(requireContext(), GRID_SPAN_COUNT)
        }
    }
}