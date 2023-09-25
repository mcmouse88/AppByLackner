package com.mcmouse88.testing_on_android.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mcmouse88.testing_on_android.R
import com.mcmouse88.testing_on_android.databinding.FragmentShoppingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingFragment : Fragment(R.layout.fragment_shopping) {

    private var _binding: FragmentShoppingBinding? = null
    private val binding: FragmentShoppingBinding
        get() = _binding ?: throw IllegalStateException("FragmentShoppingBinding is null")

    private val viewModel by activityViewModels<ShoppingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentShoppingBinding.bind(view)

        binding.fabShoppingItem.setOnClickListener {
            findNavController().navigate(
                ShoppingFragmentDirections.actionShoppingFragmentToAddShoppingItemFragment()
            )
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}