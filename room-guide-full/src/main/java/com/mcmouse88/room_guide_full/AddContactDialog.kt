package com.mcmouse88.room_guide_full

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun AddContactDialog(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onEvent(ContactEvent.HideDialog) },
        confirmButton = {
            Button(onClick = {
                onEvent(ContactEvent.SaveContact)
            }) {
                Text(text = stringResource(id = R.string.save))
            }
        },
        title = { Text(text = stringResource(id = R.string.add_contact)) },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(value = state.firstName, onValueChange = {
                    onEvent(ContactEvent.SetFirstName(it))
                },
                    placeholder = {
                        Text(text = stringResource(R.string.first_name))
                    }
                )

                TextField(value = state.lastName, onValueChange = {
                    onEvent(ContactEvent.SetLastName(it))
                },
                    placeholder = {
                        Text(text = stringResource(R.string.last_name))
                    }
                )

                TextField(value = state.phoneNumber, onValueChange = {
                    onEvent(ContactEvent.SetPhoneNumber(it))
                },
                    placeholder = {
                        Text(text = stringResource(R.string.phone_number))
                    }
                )
            }
        }
    )
}