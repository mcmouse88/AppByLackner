package com.mcmouse88.testing_on_android.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mcmouse88.testing_on_android.data.local.ShoppingItem
import com.mcmouse88.testing_on_android.data.remote.responses.ImageDto
import com.mcmouse88.testing_on_android.others.Resource

class FakeShoppingRepository : ShoppingRepository {

    private val shoppingItems = mutableListOf<ShoppingItem>()

    private val observableShoppingItems = MutableLiveData<List<ShoppingItem>>(shoppingItems)
    private val observableTotalPrice = MutableLiveData<Float>()

    private var shouldReturnNetworkError = false

    override suspend fun insertShoppingItem(item: ShoppingItem) {
        shoppingItems.add(item)
        refreshLiveData()
    }

    override suspend fun deleteShoppingItem(item: ShoppingItem) {
        shoppingItems.remove(item)
        refreshLiveData()
    }

    override fun observeAllShoppingItem(): LiveData<List<ShoppingItem>> {
        return observableShoppingItems
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return observableTotalPrice
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageDto> {
        return if (shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success(ImageDto(listOf(), 0, 0))
        }
    }

    fun setShouldReturnNetworkError(isError: Boolean) {
        shouldReturnNetworkError = isError
    }

    private fun refreshLiveData() {
        observableShoppingItems.postValue(shoppingItems)
        observableTotalPrice.postValue(getTotalPrice())
    }

    private fun getTotalPrice(): Float {
        return shoppingItems.sumOf { it.price.toDouble() }.toFloat()
    }
}