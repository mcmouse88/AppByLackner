package com.mcmouse88.encryption

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.dataStore
import com.mcmouse88.encryption.ui.theme.EncryptionTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

private const val FILE_PATH = "secret.txt"

class MainActivity : ComponentActivity() {

    private val Context.dataStore by dataStore(
        fileName = "user-settings.json",
        serializer = UserSettingsSerializer(CryptoManager())
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cryptoManager = CryptoManager()

        setContent {
            EncryptionTheme {
                var messageToEncrypt by remember { mutableStateOf("") }
                var messageToDecrypt by remember { mutableStateOf("") }

                var username by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                var settings by remember { mutableStateOf(UserSettings()) }
                val scope = rememberCoroutineScope()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    TextField(
                        value = messageToEncrypt,
                        onValueChange = { messageToEncrypt = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text(text = "Encrypt string") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        Button(
                            onClick = {
                                val bytes = messageToEncrypt.encodeToByteArray()
                                val file = File(filesDir, FILE_PATH)
                                if (!file.exists()) {
                                    file.createNewFile()
                                }
                                val fos = FileOutputStream(file)
                                messageToDecrypt = cryptoManager.encrypt(
                                    bytes = bytes,
                                    outputStream = fos
                                ).decodeToString()
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "Encrypt")
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(
                            onClick = {
                                val file = File(filesDir, FILE_PATH)
                                messageToEncrypt = cryptoManager.decrypt(
                                    inputStream = FileInputStream(file)
                                ).decodeToString()
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "Decrypt")
                        }
                    }

                    Text(text = messageToDecrypt)

                    Column(
                        modifier = Modifier
                            .fillMaxSize(1f)
                            .padding(top = 32.dp)
                    ) {
                        TextField(
                            value = username,
                            onValueChange = { username = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text(text = "Username") }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            value = password,
                            onValueChange = { password = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text(text = "Password") }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row {
                            Button(
                                onClick = {
                                    scope.launch {
                                        dataStore.updateData {
                                            UserSettings(
                                                username = username,
                                                password = password
                                            )
                                        }
                                    }
                                },
                                Modifier.weight(1f)
                            ) {
                                Text(text = "Save")
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                onClick = {
                                    scope.launch {
                                        settings = dataStore.data.first()
                                    }
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(text = "Load")
                            }
                        }
                        Text(text = settings.toString())
                    }
                }
            }
        }
    }
}