package com.alorma.tempcontacts.ui.contacts

import com.alorma.tempcontacts.domain.model.Contact
import com.alorma.tempcontacts.ui.common.State
import javax.inject.Inject

class ContactsList @Inject constructor() {
    sealed class ContactsState : State() {
        data class Items(val items: List<Contact>) : ContactsState()
    }

    fun items(it: List<Contact>): ContactsState = ContactsState.Items(it)
}