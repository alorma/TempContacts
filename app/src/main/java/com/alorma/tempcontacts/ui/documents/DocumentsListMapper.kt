package com.alorma.tempcontacts.ui.documents

import com.alorma.tempcontacts.domain.model.AppDocument
import com.alorma.tempcontacts.ui.common.State
import javax.inject.Inject

class DocumentsListMapper @Inject constructor() {
    sealed class ContactsState : State() {
        object Loading : ContactsState()
        object Empty : ContactsState()
        data class Items(val items: List<AppDocument>) : ContactsState()
    }

    fun loading(): ContactsState = ContactsState.Loading

    fun mapContacts(it: List<AppDocument>): List<AppDocument> = it

    fun items(it: List<AppDocument>): ContactsState = if (it.isEmpty()) {
        ContactsState.Empty
    } else {
        ContactsState.Items(it)
    }
}