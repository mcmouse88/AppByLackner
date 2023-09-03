package com.mcmouse88.sharing_data_between_screens.content

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Composable
fun StatefulDependencySample() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "screen1"
    ) {
        composable("screen1") {
            val viewModel = hiltViewModel<Screen1ViewModel>()
            val count by viewModel.count.collectAsStateWithLifecycle()

            Screen1(
                count = count,
                onNavigateToScreenTwo = {
                    viewModel.inc()
                    navController.navigate("screen2")
                }
            )
        }

        composable(route = "screen2") {
            val viewModel = hiltViewModel<Screen2ViewModel>()
            val count by viewModel.count.collectAsStateWithLifecycle()

            Screen2(count)
        }
    }
}

@Composable
fun Screen1(
    count: Int,
    onNavigateToScreenTwo: () -> Unit
) {
    Button(onClick = { onNavigateToScreenTwo.invoke() }) {
        Text(text = "Count on ScreenOne: $count")
    }
}

@Composable
fun Screen2(
    count: Int
) {
    Text(text = "Count on ScreenTwo: $count")
}

@Singleton
class GlobalCounter @Inject constructor() {
    private val _count = MutableStateFlow(0)
    val count = _count.asStateFlow()

    fun inc() {
        _count.value++
    }
}

@HiltViewModel
class Screen1ViewModel @Inject constructor(
    private val counter: GlobalCounter
) : ViewModel() {
    val count = counter.count

    fun inc() {
        counter.inc()
    }
}

@HiltViewModel
class Screen2ViewModel @Inject constructor(
    counter: GlobalCounter
) : ViewModel() {
    val count = counter.count
}