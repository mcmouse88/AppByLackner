package com.mcmouse88.firebase_cloud_messaging

data class PushNotification(
    val data: NotificationData,
    val to: String
)
