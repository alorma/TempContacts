package com.alorma.tempcontacts.ui.configuration

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alorma.tempcontacts.domain.model.Contact
import com.alorma.tempcontacts.domain.model.CreateContact
import com.alorma.tempcontacts.domain.repository.ContactRepository
import com.alorma.tempcontacts.domain.work.RemoveContactTask
import com.alorma.tempcontacts.extensions.actionSwitchMap
import com.alorma.tempcontacts.extensions.switchMap
import com.alorma.tempcontacts.ui.common.BaseViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class CreateDocumentViewModel @Inject constructor(
        private val options: CreateDocumentMapper,
        private val contactRepository: ContactRepository,
        private val scheduleRemoveTask: RemoveContactTask) :
        BaseViewModel() {

    private val saveLiveData: MutableLiveData<Save> = MutableLiveData()

    private val saveContact: LiveData<CreateDocumentMapper.NewState> = saveLiveData.actionSwitchMap {
        when (it) {
            Save.InvalidTime -> options.invalidTime()
            is Save.SaveContact -> {
                val timeCalculation = getTime(it.time)
                val createContact = CreateContact(it.contact.androidId, it.contact.name, timeCalculation)
                contactRepository.create(createContact)
                schedule(it.contact.androidId, timeCalculation)
                options.saveComplete()
            }
        }
    }

    private val uriLiveData: MutableLiveData<Uri> = MutableLiveData()
    private val getContactLiveData: LiveData<Contact?> = uriLiveData.switchMap {
        contactRepository.import(it)
    }

    fun onContact(uri: Uri): LiveData<Contact?> {
        uriLiveData.postValue(uri)
        return getContactLiveData
    }

    fun save(contact: Contact, time: TimeSelection): LiveData<CreateDocumentMapper.NewState> {
        val saveState = if (time == TimeSelection.NONE) {
            Save.InvalidTime
        } else {
            Save.SaveContact(contact, time)
        }
        saveLiveData.postValue(saveState)

        return saveContact
    }

    private fun getTime(time: TimeSelection): Long = when (time) {
        TimeSelection.HOUR -> TimeUnit.HOURS.toMillis(1)
        TimeSelection.DAY -> TimeUnit.DAYS.toMillis(1)
        TimeSelection.WEEK -> TimeUnit.DAYS.toMillis(7)
        TimeSelection.MONTH -> TimeUnit.DAYS.toMillis(30)
        is TimeSelection.Custom -> time.number * getTime(time.type)
        else -> 0
    }

    private fun schedule(androidId: String, time: Long) =
            scheduleRemoveTask.removeUser(androidId, time)

    sealed class Save {
        object InvalidTime : Save()
        data class SaveContact(val contact: Contact, val time: TimeSelection) : Save()
    }
}