package com.mcmouse88.biometric_auth

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager.Authenticators
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mcmouse88.biometric_auth.BiometricPromptManager.BiometricResult
import com.mcmouse88.biometric_auth.ui.theme.BiometricAuthTheme

class MainActivity : AppCompatActivity() {

    private val promptManager by lazy {
        BiometricPromptManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BiometricAuthTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val biometricResult by promptManager.promptResult.collectAsState(initial = null)

                    val enrollLauncher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartActivityForResult()
                    ) {
                        println("Activity result: $it")
                    }

                    LaunchedEffect(key1 = biometricResult) {
                        if (biometricResult is BiometricResult.AuthenticationNotSet) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                                    putExtra(
                                        Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                                        Authenticators.BIOMETRIC_STRONG or Authenticators.DEVICE_CREDENTIAL
                                    )
                                }
                                enrollLauncher.launch(enrollIntent)
                            }
                        }
                    }

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {
                                promptManager.showBiometricPrompt(
                                    title = getString(R.string.sample_prompt),
                                    description = getString(R.string.sample_prompt_description)
                                )
                            }
                        ) {
                            Text(text = stringResource(R.string.authenticate))
                        }

                        biometricResult?.let { result ->
                            val text =  when (result) {
                                is BiometricResult.AuthenticationError -> result.error
                                BiometricResult.AuthenticationFailed -> {
                                    stringResource(R.string.authentication_failed)
                                }
                                BiometricResult.AuthenticationNotSet -> {
                                    stringResource(R.string.authentication_not_set)
                                }
                                BiometricResult.AuthenticationSuccess -> {
                                    stringResource(R.string.authentication_success)
                                }
                                BiometricResult.FeatureUnavailable -> {
                                    stringResource(R.string.feature_unavailable)
                                }
                                BiometricResult.HardwareUnavailable -> {
                                    stringResource(R.string.hardware_unavailable)
                                }
                            }
                            Text(text = text)
                        }
                    }
                }
            }
        }
    }
}