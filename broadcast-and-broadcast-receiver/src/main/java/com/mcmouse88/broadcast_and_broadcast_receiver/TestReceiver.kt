package com.mcmouse88.broadcast_and_broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class TestReceiver : BroadcastReceiver() {

    private val TAG = "TestReceiver.kt"

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "TEST_ACTION") {
            Log.e(TAG, "Received test intent")
        }
    }
}