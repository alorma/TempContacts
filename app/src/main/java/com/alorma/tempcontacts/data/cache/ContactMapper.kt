package com.alorma.tempcontacts.data.cache

import com.alorma.tempcontacts.domain.model.AppDocument
import com.alorma.tempcontacts.domain.model.CreateContact
import com.alorma.tempcontacts.domain.model.Type
import javax.inject.Inject

class ContactMapper @Inject constructor() {

    fun map(contactEntity: ContactEntity): AppDocument =
            AppDocument(contactEntity.id, contactEntity.name, contactEntity.time, Type.Unknown)

    fun map(contact: CreateContact): ContactEntity =
            ContactEntity(contact.androidId, contact.name, contact.time)
}