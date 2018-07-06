package com.alorma.tempcontacts.ui.newcontact

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.alorma.tempcontacts.di.ScreenModule
import dagger.Module
import dagger.Provides

@Module
class NewContactModule(private val fragment: Fragment) : ScreenModule(fragment) {

    @Provides
    fun provideViewModel(factory: NewContactViewModelFactory): NewContactViewModel =
            ViewModelProviders.of(fragment, factory).get()

}