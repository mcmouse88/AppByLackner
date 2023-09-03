package com.mcmouse88.navigate_back_result

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mcmouse88.navigate_back_result.ui.theme.NavigateBackWithResultTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigateBackWithResultTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "screen1"
                ) {
                    composable("screen1") { entry ->
                        val text = entry.savedStateHandle.get<String>("req_key")
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            text?.let {
                                Text(text = text)
                            }
                            Button(
                                onClick = {
                                    navController.navigate("screen2")
                                }
                            ) {
                                Text(text = "Go to screen 2")
                            }
                        }
                    }

                    composable("screen2") {
                        Column(
                            Modifier.fillMaxSize()
                        ) {
                            var text by remember {
                                mutableStateOf("")
                            }
                            OutlinedTextField(
                                value = text,
                                onValueChange = { text = it },
                                modifier = Modifier.width(300.dp)
                            )

                            Button(
                                onClick = {
                                    navController.previousBackStackEntry?.savedStateHandle?.set(
                                        "req_key",
                                        text
                                    )
                                    navController.navigateUp()
                                }
                            ) {
                                Text(text = "Apply")
                            }
                        }
                    }
                }
            }
        }
    }
}