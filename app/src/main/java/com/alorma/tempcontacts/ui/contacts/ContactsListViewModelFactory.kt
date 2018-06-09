package com.alorma.tempcontacts.ui.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alorma.tempcontacts.di.DataModule
import com.alorma.tempcontacts.domain.repository.ContactRepository
import com.alorma.tempcontacts.domain.work.TestTask
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class ContactsListViewModelFactory @Inject constructor(
        private val operations: ContactsList,
        private val contactRepository: ContactRepository,
        private val tasker: TestTask,
        @Named(DataModule.IO) private val io: Scheduler,
        @Named(DataModule.IO) private val main: Scheduler
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            ContactsListViewModel(operations, contactRepository, tasker, io, main) as T
}