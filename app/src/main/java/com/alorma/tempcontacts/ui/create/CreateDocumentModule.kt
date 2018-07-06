package com.alorma.tempcontacts.ui.create

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.alorma.tempcontacts.di.ScreenModule
import dagger.Module
import dagger.Provides

@Module
class CreateDocumentModule(private val fragment: Fragment) : ScreenModule(fragment) {

    @Provides
    fun provideViewModel(factory: CreateDocumentViewModelFactory): CreateDocumentViewModel =
            ViewModelProviders.of(fragment, factory).get()

}