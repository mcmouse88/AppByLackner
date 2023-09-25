package com.mcmouse88.testing_on_android.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mcmouse88.testing_on_android.R
import com.mcmouse88.testing_on_android.databinding.FragmentAddShoppingItemBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddShoppingItemFragment : Fragment(R.layout.fragment_add_shopping_item) {

    private var _binding: FragmentAddShoppingItemBinding? = null
    private val binding: FragmentAddShoppingItemBinding
        get() = _binding ?: throw IllegalStateException("FragmentAddShoppingItemBinding is null")

    private val viewModel by activityViewModels<ShoppingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddShoppingItemBinding.bind(view)

        binding.ivShoppingItem.setOnClickListener {
            findNavController().navigate(
                AddShoppingItemFragmentDirections.actionAddShoppingItemFragmentToImagePickFragment()
            )
        }

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner) {
            viewModel.setCurrentImageUrl("")
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}