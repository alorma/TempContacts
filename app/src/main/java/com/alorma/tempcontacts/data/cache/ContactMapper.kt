package com.alorma.tempcontacts.data.cache

import com.alorma.tempcontacts.domain.model.Contact
import javax.inject.Inject

class ContactMapper @Inject constructor() {

    operator fun invoke(it: List<ContactEntity>): List<Contact> =
            it.map { Contact(it.id, it.androidId, it.name) }

    operator fun invoke(contactEntity: ContactEntity): Contact =
            Contact(contactEntity.id, contactEntity.androidId, contactEntity.name)

    operator fun invoke(contact: Contact): ContactEntity =
            ContactEntity(contact.id, contact.androidId, contact.name)
}