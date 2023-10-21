package com.mcmouse88.permissions_handling_guide

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mcmouse88.permissions_handling_guide.ui.theme.PermissionsHandlingTheme

class MainActivity : ComponentActivity() {

    private val permissionsToRequest = arrayOf(
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.CALL_PHONE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PermissionsHandlingTheme {
                val viewModel = viewModel<MainViewModel>()
                val dialogQueue = viewModel.visiblePermissionsDialogQueue

                val cameraPermissionResultLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission(),
                    onResult = { isGranted ->
                        viewModel.onPermissionResult(
                            permission = Manifest.permission.CAMERA,
                            isGranted = isGranted
                        )
                    }
                )

                val multiplePermissionResultLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestMultiplePermissions(),
                    onResult = { permissions ->
                        permissionsToRequest.forEach { permission ->
                            viewModel.onPermissionResult(
                                permission = permission,
                                isGranted = permissions[permission] == true
                            )
                        }
                    }
                )

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        cameraPermissionResultLauncher.launch(
                            Manifest.permission.CAMERA
                        )
                    }) {
                        Text(text = stringResource(R.string.request_on_permission))
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = {
                        multiplePermissionResultLauncher.launch(permissionsToRequest)
                    }) {
                        Text(text = stringResource(R.string.request_multiply_permissions))
                    }
                }

                dialogQueue
                    .reversed()
                    .forEach { permission ->
                        PermissionDialog(
                            permissionProvider = when (permission) {
                                Manifest.permission.CAMERA -> CameraPermissionTextProvider()
                                Manifest.permission.RECORD_AUDIO -> RecordAudioPermissionTextProvider()
                                Manifest.permission.CALL_PHONE -> PhoneCallPermissionTextProvider()
                                else -> return@forEach
                            },
                            isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
                                permission
                            ),
                            onDismissListener = viewModel::dismissDialog,
                            onConfirmClick = {
                                viewModel.dismissDialog()
                                multiplePermissionResultLauncher.launch(
                                    arrayOf(permission)
                                )
                            },
                            onToSettingsClick = ::openAppSettings
                        )
                    }
            }
        }
    }
}

fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}