package com.alorma.tempcontacts.domain.work

import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import javax.inject.Inject

class TestTask @Inject constructor(private val manager: WorkManager) {

    operator fun invoke() {
        val workRequest = OneTimeWorkRequest.Builder(TestWorker::class.java).build()
        manager.enqueue(workRequest)
    }
}