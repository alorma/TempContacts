package com.alorma.tempcontacts.ui.contacts

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.alorma.tempcontacts.di.ScreenModule
import dagger.Module
import dagger.Provides

@Module
class ContactsListModule(private val activity: FragmentActivity) : ScreenModule(activity) {

    @Provides
    fun provideViewModel(factory: ContactsListViewModelFactory): ContactsListViewModel =
            ViewModelProviders.of(activity, factory).get(ContactsListViewModel::class.java)

}