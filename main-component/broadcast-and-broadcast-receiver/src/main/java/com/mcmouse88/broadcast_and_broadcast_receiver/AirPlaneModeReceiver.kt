package com.mcmouse88.broadcast_and_broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log

class AirPlaneModeReceiver : BroadcastReceiver() {

    private val TAG = "AirPlaneModeReceiver.kt"

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            val isTurnedOn = Settings.Global.getInt(
                context?.contentResolver,
                Settings.Global.AIRPLANE_MODE_ON
            ) != 0
            Log.e(TAG, "Is airplane mode enabled? $isTurnedOn")
        }
    }
}