package com.alorma.tempcontacts.domain.work

import com.alorma.tempcontacts.data.cache.ContactDataSource as Cache
import com.alorma.tempcontacts.data.framework.ContactDataSource as System

/*
class DeleteSingleContactWorker : Worker() {

    companion object {
        const val ANDROID_ID = "android_id"
    }

    override fun doWork(): WorkerResult {
        val androidId = inputData.getString(ANDROID_ID, null)

        androidId?.let {
            deleteContact(androidId, it)
        }

        return WorkerResult.SUCCESS
    }

    private fun deleteContact(androidId: String?, id: String) {
        Log.i("Delete contact", androidId)

        val system = System(applicationContext)

        system.delete(id)
    }
}
        */