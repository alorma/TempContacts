package com.alorma.tempcontacts.domain.work

import androidx.work.Worker
import com.alorma.tempcontacts.TempContactsApp.Companion.component
import com.alorma.tempcontacts.domain.repository.DocumentsRepository
import javax.inject.Inject

class SyncContactsWorker : Worker() {

    @Inject
    lateinit var documentsRepository: DocumentsRepository

    override fun doWork(): Result {
        component inject this

        documentsRepository.loadDocuments().forEach {
            documentsRepository.syncLocal(it.androidId, it.uri, it.type)
        }

        return Result.SUCCESS
    }
}