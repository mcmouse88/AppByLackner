package com.mcmouse88.testing_on_android.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mcmouse88.testing_on_android.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddShoppingItemFragment : Fragment(R.layout.fragment_add_shopping_item) {

    private val viewModel by activityViewModels<ShoppingViewModel>()
}