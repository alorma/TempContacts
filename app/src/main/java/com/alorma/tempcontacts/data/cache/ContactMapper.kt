package com.alorma.tempcontacts.data.cache

import com.alorma.tempcontacts.domain.model.Contact
import com.alorma.tempcontacts.domain.model.CreateContact
import javax.inject.Inject

class ContactMapper @Inject constructor() {

    operator fun invoke(it: List<ContactEntity>): List<Contact> =
            it.map { Contact(it.id, it.androidId, it.name, it.time) }

    operator fun invoke(contactEntity: ContactEntity): Contact =
            Contact(contactEntity.id, contactEntity.androidId, contactEntity.name,
                    contactEntity.time)

    operator fun invoke(contact: Contact): ContactEntity =
            ContactEntity(contact.id, contact.androidId, contact.name, contact.time)

    operator fun invoke(contact: CreateContact): ContactEntity =
            ContactEntity(null, contact.androidId, contact.name, contact.time)
}