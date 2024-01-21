package com.mcmouse88.one_time_events

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mcmouse88.one_time_events.ui.theme.OneTimeEventsTheme
import kotlinx.coroutines.flow.Flow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OneTimeEventsTheme {
                val navController = rememberNavController()
                
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                        val viewModel = viewModel<MainViewModel>()
                        val state = viewModel.state

                        // LaunchEffect for Channel
                        ObserveAsEvents(
                            flow = viewModel.navigationEventChannelFlow,
                            onEvent = { event ->
                                when (event) {
                                    NavigationEvent.NavigateToProfile -> {
                                        navController.navigate("profile")
                                    }
                                }
                            }
                        )

                        // Launch Effect for SharedFlow
                        ObserveAsEvents(
                            flow = viewModel.navigationEventSharedFlow,
                            onEvent = { event ->
                                when (event) {
                                    NavigationEvent.NavigateToProfile -> {
                                        navController.navigate("profile")
                                       // Toast.makeText(context, "Toast", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        )
                        
                        // LaunchEffect for State
                        LaunchedEffect(key1 = state.isloggedIn) {
                            if (state.isloggedIn) {
                                navController.navigate("profile")
                                viewModel.onNavigateToLogin()
                            }
                        }


                        LoginScreen(
                            state = state,
                            onLoginClick = viewModel::loginWithSharedFlow
                        )
                    }

                    composable("profile") {
                        ProfileScreen()
                    }
                }
            }
        }
    }
}

@Composable
private fun <T> ObserveAsEvents(flow: Flow<T>, onEvent: (T) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(key1 = flow, key2 = lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect(onEvent)
        }
    }
}

@Composable
private fun LoginScreen(
    state: LoginState,
    onLoginClick: () -> Unit
) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = onLoginClick) {
                Text(text = "Login")
            }
            if (state.isLoading) {
                Spacer(modifier = Modifier.height(16.dp))
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
private fun ProfileScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Profile", fontSize = 32.sp)
    }
}