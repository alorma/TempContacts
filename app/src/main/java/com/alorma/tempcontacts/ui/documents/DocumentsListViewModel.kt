package com.alorma.tempcontacts.ui.documents

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.OnLifecycleEvent
import com.alorma.tempcontacts.domain.repository.ContactRepository
import com.alorma.tempcontacts.domain.work.CheckRemovedContactsTask
import com.alorma.tempcontacts.extensions.map
import com.alorma.tempcontacts.ui.common.BaseViewModel

class DocumentsListViewModel(private val operations: DocumentsListMapper,
                             contactRepository: ContactRepository,
                             private val checkRemovedContactsTask: CheckRemovedContactsTask) :
        BaseViewModel() {

    val documentsMapper: LiveData<DocumentsListMapper.ContactsState> = contactRepository.load().map {
        operations.mapContacts(it)
    }.map {
        operations.items(it)
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onStart() {
        scheduleCheckExisting()
    }

    private fun scheduleCheckExisting() = checkRemovedContactsTask.check()

}
