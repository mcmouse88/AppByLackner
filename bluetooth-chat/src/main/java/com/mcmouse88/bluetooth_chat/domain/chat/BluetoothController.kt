package com.mcmouse88.bluetooth_chat.domain.chat

import kotlinx.coroutines.flow.StateFlow

interface BluetoothController {
    val scannedDevices: StateFlow<List<BluetoothDeviceModel>>
    val pairedDevices: StateFlow<List<BluetoothDeviceModel>>

    fun startDiscovery()
    fun stopDiscovery()
    fun release()
}