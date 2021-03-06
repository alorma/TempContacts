package com.alorma.tempcontacts.domain.work

import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RemoveContactTask @Inject constructor(private val manager: WorkManager?) {

    fun removeDocument(androidId: String, time: Long) {
        manager?.apply {
            val data = Data.Builder()
                    .putString(DeleteSingleDocumentWorker.ANDROID_ID, androidId)
                    .build()
            val compressionWork = OneTimeWorkRequest.Builder(DeleteSingleDocumentWorker::class.java)
                    .setInputData(data)
                    .setInitialDelay(time, TimeUnit.MILLISECONDS)
                    .build()
            enqueue(compressionWork)
        }
    }
}