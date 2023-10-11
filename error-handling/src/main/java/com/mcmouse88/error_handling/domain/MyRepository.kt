package com.mcmouse88.error_handling.domain

import com.mcmouse88.error_handling.utils.Resource

interface MyRepository {
    suspend fun submitEmail(email: String): Resource<Unit>
}