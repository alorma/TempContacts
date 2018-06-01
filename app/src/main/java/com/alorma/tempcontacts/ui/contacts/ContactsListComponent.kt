package com.alorma.tempcontacts.ui.contacts

import dagger.Subcomponent

@Subcomponent(modules = [ContactsListModule::class])
interface ContactsListComponent {
    infix fun inject(contactsListActivity: ContactsListActivity)
}