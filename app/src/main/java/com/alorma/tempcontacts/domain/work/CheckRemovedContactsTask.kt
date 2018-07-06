package com.alorma.tempcontacts.domain.work

import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import javax.inject.Inject

class CheckRemovedContactsTask @Inject constructor(private val manager: WorkManager?) {

    fun check() {
        manager?.apply {
            val request = OneTimeWorkRequest.Builder(SyncContactsWorker::class.java).build()
            enqueue(request)
        }
    }
}