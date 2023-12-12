package com.mcmouse88.work_manager_guide

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.random.Random

class DownloadWorker(
    private val context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        showNotification()
        delay(5_000L)
        val response = FileApi.instance.downloadImage()
        response.body()?.let { body ->
            return withContext(Dispatchers.IO) {
                val file = File(context.cacheDir, "image.jpg")
                val outputStream = FileOutputStream(file)
                outputStream.use { stream ->
                    try {
                        stream.write(body.bytes())
                    } catch (e: IOException) {
                        return@withContext Result.failure(
                            workDataOf(
                                WorkKeys.ERROR_MSG to e.localizedMessage
                            )
                        )
                    }
                }
                Result.success(
                    workDataOf(
                        WorkKeys.IMAGE_URI to file.toUri().toString()
                    )
                )
            }
        }
        if (response.isSuccessful.not()) {
            if (response.code().toString().startsWith('5')) {
                return Result.retry()
            }
            return Result.failure(
                workDataOf(
                    WorkKeys.ERROR_MSG to "invalid server response"
                )
            )
        }
        return Result.failure(
            workDataOf(
                WorkKeys.ERROR_MSG to "Unknown error"
            )
        )
    }

    private suspend fun showNotification() {
        setForeground(
            ForegroundInfo(
                Random.nextInt(),
                NotificationCompat.Builder(context, "download_channel")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentText("Downloading...")
                    .setContentTitle("Download in progress")
                    .build()
            )
        )
    }
}