package com.alorma.tempcontacts.ui.newcontact

import android.net.Uri
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.alorma.tempcontacts.di.DataModule
import com.alorma.tempcontacts.domain.model.Contact
import com.alorma.tempcontacts.domain.model.CreateContact
import com.alorma.tempcontacts.domain.repository.ContactRepository
import com.alorma.tempcontacts.domain.work.DeleteSingleContactWorker
import com.alorma.tempcontacts.ui.common.BaseViewModel
import io.reactivex.Scheduler
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named


class NewContactViewModel @Inject constructor(
        private val options: NewContact,
        private val contactRepository: ContactRepository,
        @Named(DataModule.IO) private val io: Scheduler,
        @Named(DataModule.IO) private val main: Scheduler) :
        BaseViewModel<NewContact.NewState>() {

    fun onContact(uri: Uri) {
        val disposable = contactRepository.import(uri)
                .subscribeOn(io)
                .observeOn(main)
                .subscribe({
                    render(options.contact(it))
                }, {})
        add(disposable)
    }

    fun removeNonFinishedUser(it: Uri) {
        contactRepository.delete(it)
                .subscribeOn(io)
                .observeOn(main)
                .subscribe({

                }, {

                })
    }

    fun save(contact: Contact) {
        val time = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(1)
        val createContact = CreateContact(contact.androidId, contact.name, time)

        val disposable = contactRepository.create(createContact)
                .subscribeOn(io)
                .observeOn(main)
                .subscribe({
                    schedule(contact.androidId, time)
                }, {

                })

        add(disposable)
    }

    private fun schedule(androidId: String, time: Long) {
        val currentTime = System.currentTimeMillis()
        val delayed = time - currentTime
        val data = Data.Builder()
                .putString(DeleteSingleContactWorker.ANDROID_ID, androidId)
                .build()
        val compressionWork = OneTimeWorkRequest.Builder(DeleteSingleContactWorker::class.java)
                .setInputData(data)
                .setInitialDelay(delayed, TimeUnit.MILLISECONDS)
                .build()
        WorkManager.getInstance().enqueue(compressionWork)
    }

}