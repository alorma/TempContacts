package com.alorma.tempcontacts.ui.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ContactsListViewModelFactory @Inject constructor(
        private val operations: ContactsList) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            ContactsListViewModel(operations) as T
}