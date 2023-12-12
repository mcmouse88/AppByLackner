package com.mcmouse88.work_manager_guide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.LightingColorFilter
import android.graphics.Paint
import androidx.core.net.toFile
import androidx.core.net.toUri
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class ColorFilterWorker(
    private val context: Context,
    private val workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        val imageFile = workerParameters.inputData.getString(WorkKeys.IMAGE_URI)
            ?.toUri()
            ?.toFile()
        delay(5_000L)

        return imageFile?.let { file ->
            val btm = BitmapFactory.decodeFile(file.absolutePath)
            val resultBtm = btm.copy(btm.config, true)
            val paint = Paint().apply {
                colorFilter = LightingColorFilter(0x08FF04, 1)
            }
            val canvas = Canvas(resultBtm)
            canvas.drawBitmap(resultBtm, 0f, 0f, paint)

            withContext(Dispatchers.IO) {
                val resultImageFile = File(context.cacheDir, "filter.jpg")
                val outputStream = FileOutputStream(resultImageFile)
                val successful = resultBtm.compress(
                    Bitmap.CompressFormat.JPEG,
                    90,
                    outputStream
                )

                if (successful) {
                    Result.success(
                        workDataOf(
                            WorkKeys.FILTER_URI to resultImageFile.toUri().toString()
                        )
                    )
                } else {
                    Result.failure()
                }
            }
        } ?: Result.failure()
    }
}