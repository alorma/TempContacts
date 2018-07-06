package com.alorma.tempcontacts.ui.contacts

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.alorma.tempcontacts.di.ScreenModule
import dagger.Module
import dagger.Provides

@Module
class ContactsListModule(private val fragment: Fragment) : ScreenModule(fragment) {

    @Provides
    fun provideViewModel(factory: ContactsListViewModelFactory): ContactsListViewModel =
            ViewModelProviders.of(fragment, factory).get()

}