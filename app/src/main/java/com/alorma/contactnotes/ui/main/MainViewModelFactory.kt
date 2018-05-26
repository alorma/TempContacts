package com.alorma.contactnotes.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alorma.contactnotes.di.ScreenModule
import com.karumi.dexter.DexterBuilder
import javax.inject.Inject
import javax.inject.Named

class MainViewModelFactory @Inject constructor(
        @Named(ScreenModule.READ_CONTACT_PERMISSION)
        private val permission: DexterBuilder.SinglePermissionListener

) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            MainViewModel(permission) as T
}