package com.mcmouse88.work_manager_guide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import coil.compose.rememberAsyncImagePainter
import com.mcmouse88.work_manager_guide.ui.theme.WorkManagerGuideTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val downloadRequest = OneTimeWorkRequestBuilder<DownloadWorker>()
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .build()

        val colorFilterRequest = OneTimeWorkRequestBuilder<ColorFilterWorker>().build()
        val workManager = WorkManager.getInstance(applicationContext)

        setContent {
            WorkManagerGuideTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val workInfo = workManager
                        .getWorkInfosForUniqueWorkLiveData("download")
                        .observeAsState()
                        .value

                    val downloadInfo = remember(key1 = workInfo) {
                        workInfo?.find { it.id == downloadRequest.id }
                    }

                    val filterInfo = remember(key1 = workInfo) {
                        workInfo?.find { it.id == colorFilterRequest.id }
                    }

                    val imageUri by remember(key1 = downloadInfo, key2 = filterInfo) {
                        derivedStateOf {
                            val downloadUri =
                                downloadInfo?.outputData?.getString(WorkKeys.IMAGE_URI)
                                    ?.toUri()

                            val filterUri = filterInfo?.outputData?.getString(WorkKeys.FILTER_URI)
                                ?.toUri()

                            filterUri ?: downloadUri
                        }
                    }


                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        imageUri?.let { uri ->
                            Image(
                                painter = rememberAsyncImagePainter(model = uri),
                                contentDescription = null,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        Button(
                            onClick = {
                                workManager.beginUniqueWork(
                                    "download",
                                    ExistingWorkPolicy.KEEP,
                                    downloadRequest
                                )
                                    .then(colorFilterRequest)
                                    .enqueue()
                            },
                            enabled = downloadInfo?.state != WorkInfo.State.RUNNING
                        ) {
                            Text(text = "Start downloading")
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        when (downloadInfo?.state) {
                            WorkInfo.State.RUNNING -> Text(text = "Downloading...")
                            WorkInfo.State.SUCCEEDED -> Text(text = "Download succeeded")
                            WorkInfo.State.FAILED -> Text(text = "Download failed")
                            WorkInfo.State.ENQUEUED -> Text(text = "Download enqueued")
                            WorkInfo.State.BLOCKED -> Text(text = "Download blocked")
                            else -> { /* no-op */ }
                        }
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        when (filterInfo?.state) {
                            WorkInfo.State.RUNNING -> Text(text = "Applying filter")
                            WorkInfo.State.SUCCEEDED -> Text(text = "Filter succeeded")
                            WorkInfo.State.FAILED -> Text(text = "Filter failed")
                            WorkInfo.State.ENQUEUED -> Text(text = "Filter enqueued")
                            WorkInfo.State.BLOCKED -> Text(text = "Filter blocked")
                            else -> { /* no-op */ }
                        }
                    }
                }
            }
        }
    }
}