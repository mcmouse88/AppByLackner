package com.mcmouse88.basic_text_field_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.InputTransformation
import androidx.compose.foundation.text2.input.TextFieldBuffer
import androidx.compose.foundation.text2.input.TextFieldCharSequence
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mcmouse88.basic_text_field_2.ui.theme.BasicTextField2Theme

@OptIn(ExperimentalFoundationApi::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicTextField2Theme {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    var text1 by remember { mutableStateOf("") }
                    var text2 by remember { mutableStateOf("") }

                    val modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(16.dp)

                    BasicTextField(
                        value = text1,
                        onValueChange = { text1 = it },
                        modifier = modifier
                    )
                    
                    BasicTextField2(
                        value = text2,
                        onValueChange = { text2 = it },
                        modifier = modifier
                    )

                    val textFieldState = rememberTextFieldState()
                    BasicTextField2(
                        state = textFieldState,
                        modifier = modifier,
                        inputTransformation = AndroidInputTransformation
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
object AndroidInputTransformation : InputTransformation {
    override fun transformInput(
        originalValue: TextFieldCharSequence,
        valueWithChanges: TextFieldBuffer
    ) {
        if (!"Android".contains(valueWithChanges.asCharSequence())) {
            valueWithChanges.revertAllChanges()
        }
    }
}