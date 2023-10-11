package com.mcmouse88.error_handling.data

import com.mcmouse88.error_handling.domain.MyRepository
import com.mcmouse88.error_handling.utils.Resource
import com.mcmouse88.error_handling.utils.UiText
import kotlinx.coroutines.delay
import kotlin.random.Random

class MyRepositoryImpl : MyRepository {

    override suspend fun submitEmail(email: String): Resource<Unit> {
        delay(500L)
        return if (Random.nextBoolean()) {
            Resource.Success(Unit)
        } else {
            if (Random.nextBoolean()) {
                Resource.Error(UiText.DynamicString( "Server error"))
            } else {
                Resource.Error(UiText.DynamicString("Not authenticated error"))
            }
        }
    }
}