package com.mcmouse88.error_handling.domain

import com.mcmouse88.error_handling.R
import com.mcmouse88.error_handling.data.MyRepositoryImpl
import com.mcmouse88.error_handling.utils.Resource
import com.mcmouse88.error_handling.utils.UiText

class SubmitEmailUseCase(
    private val repository: MyRepository = MyRepositoryImpl()
) {

    suspend fun execute(email: String): Resource<Unit> {
        if (!email.contains("@")) {
            return Resource.Error(UiText.StringResource(R.string.error_invalid_email))
        }

        return repository.submitEmail(email)
    }
}