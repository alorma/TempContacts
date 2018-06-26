package com.alorma.tempcontacts.domain.work

import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RemoveContactTask @Inject constructor(private val manager: WorkManager) {
    operator fun invoke(androidId: String, time: Long) {
        val currentTime = System.currentTimeMillis()
        val delayed = time - currentTime
        val data = Data.Builder()
                .putString(DeleteSingleContactWorker.ANDROID_ID, androidId)
                .build()
        val compressionWork = OneTimeWorkRequest.Builder(DeleteSingleContactWorker::class.java)
                .setInputData(data)
                .setInitialDelay(delayed, TimeUnit.MILLISECONDS)
                .build()
        manager.enqueue(compressionWork)
    }
}