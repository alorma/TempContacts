package com.alorma.tempcontacts.di

import com.alorma.tempcontacts.ui.contacts.ContactsListComponent
import com.alorma.tempcontacts.ui.contacts.ContactsListModule
import com.alorma.tempcontacts.ui.newcontact.NewContactComponent
import com.alorma.tempcontacts.ui.newcontact.NewContactModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, DataModule::class])
interface ApplicationComponent {
    infix fun add(contactsModule: ContactsListModule): ContactsListComponent
    infix fun add(newContactModule: NewContactModule): NewContactComponent
}