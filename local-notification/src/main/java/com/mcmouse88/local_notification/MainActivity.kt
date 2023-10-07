package com.mcmouse88.local_notification

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import com.mcmouse88.local_notification.ui.theme.LocalNotificationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val service = CounterNotificationService(applicationContext)
        setContent {
            LocalNotificationTheme {

                val context = LocalContext.current
                var hasNotificationPermission by remember {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        mutableStateOf(
                            ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.POST_NOTIFICATIONS
                            ) == PackageManager.PERMISSION_GRANTED
                        )
                    } else {
                        mutableStateOf(true)
                    }
                }

                var dialogIsShown by remember {
                    mutableStateOf(false)
                }

                val permissionLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission(),
                    onResult = { isGranted ->
                        hasNotificationPermission = isGranted
                        if (isGranted.not()) {
                            dialogIsShown = true
                        }
                    }
                )

                if (dialogIsShown) {
                    ShowDialogPermission(
                        context = context,
                        onDismiss = { dialogIsShown = false }
                    )
                }

                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Button(
                        onClick = {
                            if (hasNotificationPermission) {
                                service.showNotification(Counter.value)
                            } else {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                    permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                                }
                            }
                            dialogIsShown = true
                        },
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Text(text = getString(R.string.show_notification))
                    }
                }
            }
        }
    }

    @Composable
    private fun ShowDialogPermission(
        context: Context,
        onDismiss: () -> Unit,
    ) {
        if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
            val settingsIntent = Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", context.packageName, null)
            )

            @SuppressLint("QueryPermissionsNeeded")
            if (settingsIntent.resolveActivity(context.packageManager) != null) {
                DialogBuilder(
                    isCapabilityGoToSettings = true,
                    onDismiss = { onDismiss.invoke() },
                    onContinue = { context.startActivity(settingsIntent) }
                )

            } else {
                DialogBuilder(isCapabilityGoToSettings = false, onDismiss = { onDismiss.invoke() })
            }
        } else {
            onDismiss.invoke()
        }
    }

    @Composable
    private fun DialogBuilder(
        isCapabilityGoToSettings: Boolean,
        onDismiss: () -> Unit,
        onContinue: (() -> Unit)? = null
    ) {
        Dialog(onDismissRequest = { onDismiss.invoke() }) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(text = stringResource(R.string.allow_access))
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = stringResource(R.string.turn_on_notification))
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { onDismiss.invoke() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = stringResource(R.string.later))
                    }

                    if (isCapabilityGoToSettings) {
                        Spacer(modifier = Modifier.width(16.dp))

                        Button(
                            onClick = {
                                onContinue?.invoke()
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = stringResource(R.string.continue_to))
                        }
                    }
                }
            }
        }
    }
}