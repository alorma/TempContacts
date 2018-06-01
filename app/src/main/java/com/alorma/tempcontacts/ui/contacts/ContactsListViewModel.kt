package com.alorma.tempcontacts.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactsListViewModel(private val operations: ContactsList) : ViewModel() {

    private val dataContacts: MutableLiveData<ContactsList.State> = MutableLiveData()

    fun subscribe(): LiveData<ContactsList.State> = dataContacts

    fun load() {
        dataContacts.postValue(operations.dummy())
    }
}