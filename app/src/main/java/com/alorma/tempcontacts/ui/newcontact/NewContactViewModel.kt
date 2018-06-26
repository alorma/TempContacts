package com.alorma.tempcontacts.ui.newcontact

import android.net.Uri
import com.alorma.tempcontacts.di.DataModule
import com.alorma.tempcontacts.domain.model.Contact
import com.alorma.tempcontacts.domain.model.CreateContact
import com.alorma.tempcontacts.domain.repository.ContactRepository
import com.alorma.tempcontacts.domain.work.RemoveContactTask
import com.alorma.tempcontacts.ui.common.BaseViewModel
import io.reactivex.Scheduler
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named


class NewContactViewModel @Inject constructor(
        private val options: NewContact,
        private val contactRepository: ContactRepository,
        private val scheduleRemoveTask: RemoveContactTask,
        @Named(DataModule.IO) private val io: Scheduler,
        @Named(DataModule.MAIN) private val main: Scheduler) :
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
        add(contactRepository.delete(it)
                .subscribeOn(io)
                .observeOn(main)
                .subscribe({

                }, {

                }))
    }

    fun save(contact: Contact, time: TimeSelection) {
        if (time != TimeSelection.NONE) {
            saveOnValidTime(time, contact)
        } else {
            render(options.invalidTime())
        }
    }

    private fun saveOnValidTime(time: TimeSelection, contact: Contact) {
        val timeCalculation = getTime(time)

        val createContact = CreateContact(contact.androidId, contact.name, timeCalculation)

        val disposable = contactRepository.create(createContact)
                .subscribeOn(io)
                .observeOn(main)
                .subscribe({
                    schedule(contact.androidId, timeCalculation)
                    render(options.saveComplete())
                }, {

                })

        add(disposable)
    }

    private fun getTime(time: TimeSelection): Long = when (time) {
        TimeSelection.HOUR -> TimeUnit.HOURS.toMillis(1)
        TimeSelection.DAY -> TimeUnit.DAYS.toMillis(1)
        TimeSelection.WEEK -> TimeUnit.DAYS.toMillis(7)
        TimeSelection.MONTH -> TimeUnit.DAYS.toMillis(30)
        is TimeSelection.Custom -> time.number * getTime(time.type)
        else -> 0
    }

    private fun schedule(androidId: String, time: Long) = scheduleRemoveTask(androidId, time)

}