package com.mcmouse88.bluetooth_chat.data.chat

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import com.mcmouse88.bluetooth_chat.domain.chat.BluetoothDeviceModel

@SuppressLint("MissingPermission")
fun BluetoothDevice.toModel(): BluetoothDeviceModel {
    return BluetoothDeviceModel(
        name = name,
        address = address
    )
}