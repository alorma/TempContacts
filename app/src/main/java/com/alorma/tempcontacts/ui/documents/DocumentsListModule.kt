package com.alorma.tempcontacts.ui.documents

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.alorma.tempcontacts.di.ScreenModule
import com.alorma.tempcontacts.ui.documents.DocumentsListViewModel
import com.alorma.tempcontacts.ui.documents.DocumentsListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class DocumentsListModule(private val fragment: Fragment) : ScreenModule(fragment) {

    @Provides
    fun provideViewModel(factory: DocumentsListViewModelFactory): DocumentsListViewModel =
            ViewModelProviders.of(fragment, factory).get()

}