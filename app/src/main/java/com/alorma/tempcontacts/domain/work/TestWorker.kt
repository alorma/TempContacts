package com.alorma.tempcontacts.domain.work

import androidx.work.Worker

class TestWorker: Worker() {
    override fun doWork(): WorkerResult = WorkerResult.SUCCESS
}