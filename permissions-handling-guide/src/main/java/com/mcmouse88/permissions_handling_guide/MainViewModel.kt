package com.mcmouse88.permissions_handling_guide

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val visiblePermissionsDialogQueue = mutableStateListOf<String>()

    fun dismissDialog() {
        visiblePermissionsDialogQueue.removeFirst()
    }

    fun onPermissionResult(
        permission: String,
        isGranted: Boolean
    ) {
        if (isGranted.not() && !visiblePermissionsDialogQueue.contains(permission)) {
            visiblePermissionsDialogQueue.add(permission)
        }
    }
}