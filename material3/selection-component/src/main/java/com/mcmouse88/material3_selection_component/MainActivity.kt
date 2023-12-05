package com.mcmouse88.material3_selection_component

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import com.mcmouse88.material3_selection_component.ui.theme.Material3SelectionComponentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Material3SelectionComponentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        CheckBoxes()

                        Spacer(modifier = Modifier.height(32.dp))

                        MySwitch()

                        Spacer(modifier = Modifier.height(32.dp))

                        RadioGroup()
                    }
                }
            }
        }
    }
}

data class ToggleableInfo(
    val isChecked: Boolean,
    val text: String
)

@Composable
private fun RadioGroup() {
    val radioButtons = remember {
        mutableStateListOf(
            ToggleableInfo(
                isChecked = true,
                text = "Photos"
            ),
            ToggleableInfo(
                isChecked = false,
                text = "Videos"
            ),
            ToggleableInfo(
                isChecked = false,
                text = "Audio"
            )
        )
    }

    radioButtons.forEachIndexed { index, info ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable {
                    radioButtons.replaceAll {
                        it.copy(
                            isChecked = it.text == info.text
                        )
                    }
                }
                .padding(end = 16.dp)
        ) {
            RadioButton(
                selected = info.isChecked,
                onClick = {
                    radioButtons.replaceAll {
                        it.copy(
                            isChecked = it.text == info.text
                        )
                    }
                }
            )
            Text(text = info.text)
        }
    }
}

@Composable
private fun MySwitch() {
    var switch by remember {
        mutableStateOf(
            ToggleableInfo(
                isChecked = false,
                text = "Dark Mode"
            )
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = switch.text)
        Spacer(modifier = Modifier.weight(1f))
        Switch(
            checked = switch.isChecked,
            onCheckedChange = { isChecked ->
                switch = switch.copy(isChecked = isChecked)
            },
            thumbContent = {
                Icon(
                    imageVector = if (switch.isChecked) {
                        Icons.Default.Check
                    } else {
                        Icons.Default.Close
                    },
                    contentDescription = null
                )
            }
        )
    }
}

@Composable
private fun CheckBoxes() {
    val checkboxes = remember {
        mutableStateListOf(
            ToggleableInfo(
                isChecked = false,
                text = "Photos"
            ),
            ToggleableInfo(
                isChecked = false,
                text = "Videos"
            ),
            ToggleableInfo(
                isChecked = false,
                text = "Audio"
            )
        )
    }

    var triState by remember {
        mutableStateOf(ToggleableState.Indeterminate)
    }

    val toggleTriState = {
        triState = when (triState) {
            ToggleableState.On -> ToggleableState.Off
            ToggleableState.Off -> ToggleableState.On
            ToggleableState.Indeterminate -> ToggleableState.On
        }

        checkboxes.indices.forEach { index ->
            checkboxes[index] = checkboxes[index].copy(
                isChecked = triState == ToggleableState.On
            )
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                toggleTriState.invoke()
            }
            .padding(end = 16.dp)
    ) {
        TriStateCheckbox(
            state = triState,
            onClick = toggleTriState
        )
        Text(text = stringResource(id = R.string.file_types))
    }

    checkboxes.forEachIndexed { index, info ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable {
                    checkboxes[index] = info.copy(isChecked = !info.isChecked)
                }
                .padding(end = 16.dp)
        ) {
            Checkbox(
                checked = info.isChecked,
                onCheckedChange = { isChecked ->
                    checkboxes[index] = info.copy(isChecked = isChecked)
                }
            )
            Text(text = info.text)
        }
    }
}
