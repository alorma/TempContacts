package com.alorma.tempcontacts.ui.contacts

import com.alorma.tempcontacts.domain.model.Contact
import com.alorma.tempcontacts.ui.common.State
import javax.inject.Inject

class ContactsList @Inject constructor() {
    sealed class ContactsState : State() {
        object Loading : ContactsState()
        object Empty : ContactsState()
        data class Items(val items: List<Contact>) : ContactsState()
    }

    fun loading(): ContactsState = ContactsState.Loading

    fun mapContacts(it: List<Contact>): List<Contact> = it

    fun items(it: List<Contact>): ContactsState = if (it.isEmpty()) {
        ContactsState.Empty
    } else {
        ContactsState.Items(it)
    }
}