package com.mcmouse88.text_fields_material3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MonitorWeight
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mcmouse88.text_fields_material3.ui.theme.TextFieldMaterial3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextFieldMaterial3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        var filledText by remember { mutableStateOf("") }
                        TextField(
                            value = filledText,
                            onValueChange = { filledText = it },
                            textStyle = LocalTextStyle.current.copy(
                                textAlign = TextAlign.Right
                            ),
                            label = {
                                Text(text = stringResource(R.string.enter_your_weight))
                            },
                            placeholder = {
                                Text(text = stringResource(id = R.string.weigth))
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.MonitorWeight,
                                    contentDescription = stringResource(id = R.string.weigth)
                                )
                            },
                            suffix = {
                                Text(text = stringResource(R.string.kg))
                            },
                            supportingText = {
                                Text(
                                    text = stringResource(R.string.required)
                                )
                            },
                            isError = false,
                            // Visual transformation for text - example for password input
                            // visualTransformation = PasswordVisualTransformation()
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Decimal,
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    println("Hello World!")
                                }
                            )
                        )
                        
                        Spacer(modifier = Modifier.height(32.dp))

                        var outLinedText by remember {
                            mutableStateOf("")
                        }

                        OutlinedTextField(
                            value = outLinedText,
                            onValueChange = { outLinedText = it },
                            textStyle = LocalTextStyle.current.copy(
                                textAlign = TextAlign.Right
                            ),
                            label = {
                                Text(text = stringResource(R.string.enter_your_weight))
                            },
                            placeholder = {
                                Text(text = stringResource(id = R.string.weigth))
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.MonitorWeight,
                                    contentDescription = stringResource(id = R.string.weigth)
                                )
                            },
                            suffix = {
                                Text(text = stringResource(R.string.kg))
                            },
                            supportingText = {
                                Text(
                                    text = stringResource(R.string.required)
                                )
                            },
                            isError = false,
                            // Visual transformation for text - example for password input
                            // visualTransformation = PasswordVisualTransformation()
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Decimal,
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    println("Hello World!")
                                }
                            )
                        )
                    }
                }
            }
        }
    }
}