package com.mcmouse88.error_handling.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcmouse88.error_handling.domain.SubmitEmailUseCase
import com.mcmouse88.error_handling.utils.Resource
import com.mcmouse88.error_handling.utils.UiText
import kotlinx.coroutines.launch

class MainViewModel(
    private val submitEmailUseCase: SubmitEmailUseCase = SubmitEmailUseCase()
) : ViewModel() {

    var email by mutableStateOf("")
        private set

    var error by mutableStateOf<UiText?>(null)
        private set

    fun onEmailChanged(email: String) {
        this.email = email
    }

    fun submitEmail() {
        viewModelScope.launch {
            when (val result = submitEmailUseCase.execute(email)) {
                is Resource.Success -> {

                }
                is Resource.Error -> {
                    error = result.message
                }
            }
        }
    }
}