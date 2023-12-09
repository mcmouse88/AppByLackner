package com.mcmouse88.nested_graph_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.mcmouse88.nested_graph_compose.ui.theme.NestedGraphComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NestedGraphComposeTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "home",
                    builder = {
                        composable(route = "about") {

                        }

                        navigation(
                            startDestination = "login",
                            route = "auth"
                        ) {
                            composable("login") {
                                val viewModel = it.sharedViewModel<SampleViewModel>(navController = navController)

                                Button(
                                    onClick = {
                                        navController.navigate("calendar") {
                                            popUpTo("auth") {
                                                inclusive = true
                                            }
                                        }
                                    }
                                ) {

                                }
                            }

                            composable("register") {
                                val viewModel = it.sharedViewModel<SampleViewModel>(navController = navController)
                            }

                            composable("forgot_password") {
                                val viewModel = it.sharedViewModel<SampleViewModel>(navController = navController)
                            }
                        }

                        navigation(
                            startDestination = "calendar_overview",
                            route = "calendar"
                        ) {
                            composable(route = "calendar_overview") {

                            }

                            composable(route = "calendar_entry") {

                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(key1 = this) {
        navController.getBackStackEntry(navGraphRoute)
    }

    return viewModel(parentEntry)
}