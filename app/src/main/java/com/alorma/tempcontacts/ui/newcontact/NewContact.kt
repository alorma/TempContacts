package com.alorma.tempcontacts.ui.newcontact

import com.alorma.tempcontacts.domain.model.Contact
import com.alorma.tempcontacts.ui.common.State
import javax.inject.Inject

class NewContact @Inject constructor() {
    sealed class NewState : State() {
        data class ContactImport(val contact: Contact) : NewState()
    }

    fun contact(it: Contact): NewState = NewState.ContactImport(it)
}