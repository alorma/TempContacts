package com.alorma.tempcontacts.ui.newcontact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alorma.tempcontacts.di.DataModule
import com.alorma.tempcontacts.domain.repository.ContactRepository
import com.alorma.tempcontacts.domain.work.RemoveContactTask
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class NewContactViewModelFactory @Inject constructor(
        private val options: NewContact,
        private val contactRepository: ContactRepository,
        private val scheduleRemoveTask: RemoveContactTask,
        @Named(DataModule.IO) private val io: Scheduler,
        @Named(DataModule.IO) private val main: Scheduler
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            NewContactViewModel(options, contactRepository, scheduleRemoveTask, io, main) as T
}