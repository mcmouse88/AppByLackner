package com.mcmouse88.bluetooth_chat.presentation

import com.mcmouse88.bluetooth_chat.domain.chat.BluetoothDeviceModel

data class BluetoothUiState(
    val scannedDevices: List<BluetoothDeviceModel> = emptyList(),
    val pairedDevices: List<BluetoothDeviceModel> = emptyList()
)
