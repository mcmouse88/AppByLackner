package com.mcmouse88.testing_on_android.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.google.android.material.snackbar.Snackbar
import com.mcmouse88.testing_on_android.R
import com.mcmouse88.testing_on_android.databinding.FragmentAddShoppingItemBinding
import com.mcmouse88.testing_on_android.others.Status
import javax.inject.Inject

class AddShoppingItemFragment @Inject constructor(
    val glide: RequestManager
) : Fragment(R.layout.fragment_add_shopping_item) {

    private var _binding: FragmentAddShoppingItemBinding? = null
    private val binding: FragmentAddShoppingItemBinding
        get() = _binding ?: throw IllegalStateException("FragmentAddShoppingItemBinding is null")

    lateinit var viewModel: ShoppingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddShoppingItemBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity())[ShoppingViewModel::class.java]

        subscribeToObservers()

        binding.btnAddShoppingItem.setOnClickListener {
            viewModel.insertShoppingItem(
                name = binding.etShoppingItemName.text.toString(),
                amountString = binding.etShoppingItemAmount.text.toString(),
                priceString = binding.etShoppingItemPrice.text.toString()
            )
        }

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

    private fun subscribeToObservers() {
        viewModel.currentImageUrl.observe(viewLifecycleOwner) {
            glide.load(it).into(binding.ivShoppingItem)
        }

        viewModel.insertShoppingItemStatus.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        Snackbar.make(
                            binding.root,
                            "Added shopping item",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        findNavController().navigateUp()
                    }
                    Status.ERROR -> {
                        Snackbar.make(
                            binding.root,
                            result.message ?: "Unknown error occurred",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                    Status.LOADING -> { /* no-op */ }
                }
            }
        }
    }
}