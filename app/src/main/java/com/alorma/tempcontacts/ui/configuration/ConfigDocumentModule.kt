package com.alorma.tempcontacts.ui.configuration

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.alorma.tempcontacts.di.ScreenModule
import dagger.Module
import dagger.Provides

@Module
class ConfigDocumentModule(private val fragment: Fragment) : ScreenModule(fragment) {

    @Provides
    fun provideViewModel(factory: ConfigDocumentViewModelFactory): ConfigDocumentViewModel =
            ViewModelProviders.of(fragment, factory).get()

}