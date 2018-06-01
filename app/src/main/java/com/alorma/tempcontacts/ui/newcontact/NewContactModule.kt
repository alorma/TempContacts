package com.alorma.tempcontacts.ui.newcontact

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.alorma.tempcontacts.di.ScreenModule
import dagger.Module
import dagger.Provides

@Module
class NewContactModule(private val activity: FragmentActivity) : ScreenModule(activity) {

    @Provides
    fun provideViewModel(factory: NewContactViewModelFactory): NewContactViewModel =
            ViewModelProviders.of(activity, factory).get(NewContactViewModel::class.java)

}