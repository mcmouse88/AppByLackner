package com.mcmouse88.testing_on_android.repository

import androidx.lifecycle.LiveData
import com.mcmouse88.testing_on_android.data.local.ShoppingDao
import com.mcmouse88.testing_on_android.data.local.ShoppingItem
import com.mcmouse88.testing_on_android.data.remote.PixabayApi
import com.mcmouse88.testing_on_android.data.remote.responses.ImageDto
import com.mcmouse88.testing_on_android.others.Resource
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixabayApi: PixabayApi
) : ShoppingRepository {

    override suspend fun insertShoppingItem(item: ShoppingItem) {
        shoppingDao.insertShoppingItem(item)
    }

    override suspend fun deleteShoppingItem(item: ShoppingItem) {
        shoppingDao.deleteShoppingItem(item)
    }

    override fun observeAllShoppingItem(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItem()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageDto> {
        return try {
            val response = pixabayApi.searchForImage(imageQuery)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occurred", null)
            } else {
                Resource.error("An unknown error occurred", null)
            }
        } catch (e: Exception) {
            Resource.error(message = "Couldn't reach the server. Check your internet connection", null)
        }
    }
}