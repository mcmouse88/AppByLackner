package com.mcmouse88.testing_on_android.repository

import androidx.lifecycle.LiveData
import com.mcmouse88.testing_on_android.data.local.ShoppingItem
import com.mcmouse88.testing_on_android.data.remote.responses.ImageDto
import com.mcmouse88.testing_on_android.others.Resource

interface ShoppingRepository {

    suspend fun insertShoppingItem(item: ShoppingItem)

    suspend fun deleteShoppingItem(item: ShoppingItem)

    fun observeAllShoppingItem(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageDto>
}