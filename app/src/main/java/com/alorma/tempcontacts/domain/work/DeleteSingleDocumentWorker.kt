package com.alorma.tempcontacts.domain.work

import androidx.work.Worker
import com.alorma.tempcontacts.TempContactsApp.Companion.component
import com.alorma.tempcontacts.domain.repository.DocumentsRepository
import javax.inject.Inject
import com.alorma.tempcontacts.data.cache.DocumentsDataSource as Cache
import com.alorma.tempcontacts.data.framework.DocumentsDataSource as System

class DeleteSingleDocumentWorker : Worker() {

    @Inject
    lateinit var documentsRepository: DocumentsRepository

    override fun doWork(): Result {
        component inject this

        val androidId = inputData.getString(ANDROID_ID, null)

        androidId?.let {
            documentsRepository.loadDocument(androidId)?.let {
                documentsRepository.delete(it.androidId, it.uri, it.type)
            }
        }

        return Result.SUCCESS
    }

    companion object {
        const val ANDROID_ID = "android_id"
    }
}