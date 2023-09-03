package com.mcmouse88.sharing_data_between_screens.content

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun NavArgsSample() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "screen1") {
        composable("screen1") {
            ScreenOne(
                onNavigateToScreenTwo = { stringArg ->
                    navController.navigate("screen2/$stringArg")
                }
            )
        }

        composable(
            route = "screen2/{params}",
            arguments = listOf(
                navArgument("params") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            val param = entry.arguments?.getString("params") ?: ""
            ScreenTwo(param = param)
        }
    }
}

@Composable
fun ScreenOne(
    onNavigateToScreenTwo: (String) -> Unit
) {
   Button(onClick = {
       onNavigateToScreenTwo.invoke("Hello World!")
   }) {
        Text(text = "Click me")
   } 
}

@Composable
fun ScreenTwo(
    param: String
) {
    Text(text = param)
}