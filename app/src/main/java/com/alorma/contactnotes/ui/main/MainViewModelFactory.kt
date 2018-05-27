package com.alorma.contactnotes.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alorma.contactnotes.domain.repository.ContactRepository
import com.karumi.dexter.DexterBuilder
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
        private val permission: DexterBuilder.Permission,
        private val navigation: MainNavigation,
        private val repository: ContactRepository) :
        ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            MainViewModel(permission, navigation, repository) as T
}