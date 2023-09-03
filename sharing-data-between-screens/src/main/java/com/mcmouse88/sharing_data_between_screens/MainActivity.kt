package com.mcmouse88.sharing_data_between_screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mcmouse88.sharing_data_between_screens.content.AppRoot
import com.mcmouse88.sharing_data_between_screens.content.NavArgsSample
import com.mcmouse88.sharing_data_between_screens.content.PersistentStorageSample
import com.mcmouse88.sharing_data_between_screens.content.SharedViewModelSample
import com.mcmouse88.sharing_data_between_screens.content.StatefulDependencySample
import com.mcmouse88.sharing_data_between_screens.ui.theme.SharingDataBetweenScreensTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            SharingDataBetweenScreensTheme {
                Surface {
                    NavHost(navController = navController, startDestination = "options") {

                        composable("options") {
                            ChooseSaveDataOption(navController = navController)
                        }

                        composable("nav_args") {
                            NavArgsSample()
                        }

                        composable("shared_viewmodel") {
                            SharedViewModelSample()
                        }

                        composable("stateful_deps") {
                            StatefulDependencySample()
                        }

                        composable("composition_local") {
                            AppRoot()
                        }

                        composable("persistent_storage") {
                            PersistentStorageSample()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ChooseSaveDataOption(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = {
                navController.navigate("nav_args")
            },
            modifier = Modifier.width(200.dp)
        ) {
            Text(text = "Nav Args")
        }

        Button(
            onClick = {
                navController.navigate("shared_viewmodel")
            },
            modifier = Modifier.width(200.dp)
        ) {
            Text(text = "Shared ViewModel")
        }

        Button(
            onClick = {
                navController.navigate("stateful_deps")
            },
            modifier = Modifier.width(200.dp)
        ) {
            Text(text = "Stateful Deps")
        }

        Button(
            onClick = {
                navController.navigate("composition_local")
            },
            modifier = Modifier.width(200.dp)
        ) {
            Text(text = "Composition Local")
        }

        Button(
            onClick = {
                navController.navigate("persistent_storage")
            },
            modifier = Modifier.width(200.dp)
        ) {
            Text(text = "Persistent Storage")
        }
    }
}