package com.mcmouse88.sharing_data_between_screens.content

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun SharedViewModelSample() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "on_boarding") {
        navigation(
            startDestination = "personal_details",
            route = "on_boarding"
        ) {
            composable("personal_details") { entry ->
                val viewModel = entry.sharedViewModel<SharedViewModel>(navController)
                val state by viewModel.sharedState.collectAsStateWithLifecycle()

                PersonalDetailsScreen(
                    sharedState = state,
                    onNavigate = {
                        viewModel.updateState()
                        navController.navigate("terms_and_conditions")
                    }
                )
            }

            composable("terms_and_conditions") { entry ->
                val viewModel = entry.sharedViewModel<SharedViewModel>(navController)
                val state by viewModel.sharedState.collectAsStateWithLifecycle()

                TermsAndConditionsScreen(
                    sharedState = state,
                    onBoardingFinished = {
                        navController.navigate(route = "other_screen") {
                            popUpTo("on_boarding") {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable("other_screen") {
                Text(text = "Hello World!")
            }
        }
    }
}

class SharedViewModel : ViewModel() {

    private val _sharedState = MutableStateFlow(0)
    val sharedState = _sharedState.asStateFlow()

    fun updateState() {
        _sharedState.value++
    }

    override fun onCleared() {
        super.onCleared()
        println("ViewModel cleared")
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}

@Composable
private fun PersonalDetailsScreen(
    sharedState: Int,
    onNavigate: () -> Unit
) {
    Button(onClick = { onNavigate.invoke() }) {
        Text(text = "Click me")
    }
}

@Composable
fun TermsAndConditionsScreen(
    sharedState: Int,
    onBoardingFinished: () -> Unit
) {
    Button(onClick = { onBoardingFinished.invoke() }) {
        Text(text = "State: $sharedState")
    }
}