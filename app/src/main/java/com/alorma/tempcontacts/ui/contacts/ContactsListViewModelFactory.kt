package com.alorma.tempcontacts.ui.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alorma.tempcontacts.domain.repository.ContactRepository
import com.alorma.tempcontacts.domain.work.CheckRemovedContactsTask
import com.alorma.tempcontacts.domain.work.TestTask
import javax.inject.Inject

class ContactsListViewModelFactory @Inject constructor(
        private val operations: ContactsList,
        private val contactRepository: ContactRepository,
        private val tasker: CheckRemovedContactsTask
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            ContactsListViewModel(operations, contactRepository, tasker) as T
}