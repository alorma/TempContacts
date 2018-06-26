package com.alorma.tempcontacts.data.cache

import com.alorma.tempcontacts.domain.model.Contact
import com.alorma.tempcontacts.domain.model.CreateContact
import javax.inject.Inject

class ContactMapper @Inject constructor() {

    fun map(contactEntity: ContactEntity): Contact =
            Contact(contactEntity.id, contactEntity.name, contactEntity.time)

    fun map(contact: CreateContact): ContactEntity =
            ContactEntity(contact.androidId, contact.name, contact.time)
}