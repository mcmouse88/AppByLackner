package com.mcmouse88.firebase_push_fcm

import androidx.compose.runtime.Immutable

@Immutable
data class ChatState(
    val isEnteringToken: Boolean = true,
    val remoteToken: String = "",
    val messageText: String = ""
)
