package com.alorma.tempcontacts.ui.contacts

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.Transformations
import com.alorma.tempcontacts.domain.repository.ContactRepository
import com.alorma.tempcontacts.domain.work.TestTask
import com.alorma.tempcontacts.ui.common.BaseViewModel

class ContactsListViewModel(private val operations: ContactsList,
                            private val contactRepository: ContactRepository,
                            private val testTask: TestTask) :
        BaseViewModel<ContactsList.ContactsState>() {

    val contacts: LiveData<ContactsList.ContactsState> = Transformations.map(contactRepository.load()) {
        operations.items(it)
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onStart() {
        scheduleRemove()
    }

    private fun scheduleRemove() = testTask.scheduleRemoveOldContacts()
}
