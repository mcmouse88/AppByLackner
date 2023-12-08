package com.mcmouse88.alarm_manager

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.time.ZoneId

class AndroidAlarmScheduler(
    private val context: Context
) : AlarmScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    /**
     * Methods [AlarmManager] need the permission ***SCHEDULE_EXACT_ALARM***. Through instead it
     * may use ***USE_EXACT_ALARM***. The main difference between they is ***USE_EXACT_ALARM***
     * doesn't need user granted permission unlike from ***SCHEDULE_EXACT_ALARM***. Use
     * ***USE_EXACT_ALARM*** in a simple app like a calendar or an alarm, otherwise an app could be
     * decline by Google Play.
     * @see  android.Manifest.permission.SCHEDULE_EXACT_ALARM
     * @see android.Manifest.permission.USE_EXACT_ALARM
     */
    @SuppressLint("MissingPermission")
    override fun schedule(item: AlarmItem) {

        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(AlarmReceiver.EXTRA_MESSAGE, item.message)
        }

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            item.time.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000L,
            createPendingIntent(item.hashCode(), intent)
        )
    }

    override fun cancel(item: AlarmItem) {
        alarmManager.cancel(
            createPendingIntent(
                item.hashCode(),
                Intent(context, AlarmReceiver::class.java)
            )
        )
    }

    /**
     * Instead hashcode may use a unique id in [PendingIntent]. The flag
     * [PendingIntent.FLAG_UPDATE_CURRENT] means update existing intent if it exist.
     * The flag [PendingIntent.FLAG_MUTABLE] means [PendingIntent] can't be changed.
     */
    private fun createPendingIntent(id: Int, intent: Intent): PendingIntent {
        return PendingIntent.getBroadcast(
            context,
            id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }
}