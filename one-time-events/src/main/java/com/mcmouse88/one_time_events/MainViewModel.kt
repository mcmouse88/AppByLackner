package com.mcmouse88.one_time_events

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class MainViewModel : ViewModel() {

    private val navigationChannel = Channel<NavigationEvent>()
    val navigationEventChannelFlow = navigationChannel.receiveAsFlow()

    private val _navigationEvent = MutableSharedFlow<NavigationEvent>(
        replay = 3
    )
    val navigationEventSharedFlow = _navigationEvent.asSharedFlow()

    var state by mutableStateOf(LoginState())
        private set

    var isLoggedIn by mutableStateOf(false)
        private set

    fun loginWithChannel() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            delay(3.seconds)

            navigationChannel.send(NavigationEvent.NavigateToProfile)

            state = state.copy(isLoading = false)
        }
    }

    fun loginWithSharedFlow() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            delay(3.seconds)

            _navigationEvent.tryEmit(NavigationEvent.NavigateToProfile)

            state = state.copy(isLoading = false)
        }
    }

    fun loginWithState() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            delay(3.seconds)

            state = state.copy(
                isLoading = false,
                isloggedIn = true
            )
        }
    }

    fun onNavigateToLogin() {
        state = state.copy(isloggedIn = false)
    }
}

sealed interface NavigationEvent {
    data object NavigateToProfile : NavigationEvent
}

data class LoginState(
    val isLoading: Boolean = false,
    val isloggedIn: Boolean = false
)