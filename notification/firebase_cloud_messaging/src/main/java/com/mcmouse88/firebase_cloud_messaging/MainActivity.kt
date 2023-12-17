package com.mcmouse88.firebase_cloud_messaging

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import com.mcmouse88.firebase_cloud_messaging.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val TOPIC = "/topics/my_topic"

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity.kt"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        NotificationService.sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful.not()) return@addOnCompleteListener
            NotificationService.token = task.result
            binding.etToken.setText(task.result)
        }
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)

        binding.btnSend.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val message = binding.etMessage.text.toString()
            val recipientToken = binding.etToken.text.toString()
            if (title.isNotBlank() && message.isNotBlank() && recipientToken.isNotBlank()) {
                PushNotification(
                    data = NotificationData(title, message),
                    to = TOPIC
                ).also {
                    sendNotification(it)
                }
            }
        }
    }

    private fun sendNotification(notification: PushNotification) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.api.postNotification(notification)
                if (response.isSuccessful) {
                   Log.d(TAG, "Response: ${Gson().toJson(response.body())}")
                } else {
                    Log.e(TAG, response.errorBody()?.string() ?: response.errorBody().toString())
                }
            } catch (e: Exception) {
                Log.e(TAG, e.toString(), e)
            }
        }
    }
}