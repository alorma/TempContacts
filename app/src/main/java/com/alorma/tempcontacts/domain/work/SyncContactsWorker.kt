package com.alorma.tempcontacts.domain.work

import androidx.work.Worker
import com.alorma.tempcontacts.TempContactsApp.Companion.component
import com.alorma.tempcontacts.domain.repository.ContactRepository
import javax.inject.Inject

class SyncContactsWorker : Worker() {

    @Inject
    lateinit var contactRepository: ContactRepository

    override fun doWork(): Result {
        component inject this

        contactRepository.loadContacts().forEach {
            val exist = contactRepository.exist(it)
            if (!exist) {
                contactRepository.delete(it.androidId)
            }
        }

        return Result.SUCCESS
    }
}