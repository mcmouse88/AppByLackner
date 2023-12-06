package com.mcmouse88.bottomsheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.mcmouse88.bottomsheet.ui.theme.Material3BottomSheetTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Material3BottomSheetTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val modalSheetState = rememberModalBottomSheetState()
                    val scaffoldSheetState = rememberBottomSheetScaffoldState()
                    var isModalSheetOpen by rememberSaveable {
                        mutableStateOf(false)
                    }
                    val scope = rememberCoroutineScope()

                    if (isModalSheetOpen) {
                        ModalBottomSheet(
                            sheetState = modalSheetState,
                            onDismissRequest = {
                                isModalSheetOpen = false
                            }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.kermit),
                                contentDescription = null
                            )
                        }
                    }

                    BottomSheetScaffold(
                        scaffoldState = scaffoldSheetState,
                        sheetContent = {
                            Image(
                                painter = painterResource(id = R.drawable.kermit_2),
                                contentDescription = null
                            )
                        },
                        // Hide Non-modal dialog
                        // sheetPeekHeight = 0.dp
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(onClick = {
                                isModalSheetOpen = true
                            }) {
                                Text(text = stringResource(R.string.open_modal_sheet))
                            }

                            Button(onClick = {
                                scope.launch {
                                    scaffoldSheetState.bottomSheetState.expand()
                                }
                            }) {
                                Text(text = stringResource(R.string.open_scaffold_sheet))
                            }
                        }
                    }
                }
            }
        }
    }
}