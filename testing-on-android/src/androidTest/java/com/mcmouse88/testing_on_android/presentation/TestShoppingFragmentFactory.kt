package com.mcmouse88.testing_on_android.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.mcmouse88.testing_on_android.adapters.ImageAdapter
import com.mcmouse88.testing_on_android.adapters.ShoppingItemAdapter
import com.mcmouse88.testing_on_android.repository.FakeShoppingRepositoryAndroidTest
import javax.inject.Inject

class TestShoppingFragmentFactory @Inject constructor(
    private val imageAdapter: ImageAdapter,
    private val glide: RequestManager,
    private val shoppingItemAdapter: ShoppingItemAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ImagePickFragment::class.java.name -> ImagePickFragment(imageAdapter)
            AddShoppingItemFragment::class.java.name -> AddShoppingItemFragment(glide)
            ShoppingFragment::class.java.name -> ShoppingFragment(
                shoppingItemAdapter,
                ShoppingViewModel(FakeShoppingRepositoryAndroidTest())
            )
            else -> return super.instantiate(classLoader, className)
        }
    }
}