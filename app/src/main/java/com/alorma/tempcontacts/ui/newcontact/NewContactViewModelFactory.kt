package com.alorma.tempcontacts.ui.newcontact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alorma.tempcontacts.domain.repository.ContactRepository
import com.alorma.tempcontacts.domain.work.RemoveContactTask
import javax.inject.Inject

class NewContactViewModelFactory @Inject constructor(
        private val options: NewContact,
        private val contactRepository: ContactRepository,
        private val scheduleRemoveTask: RemoveContactTask
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            NewContactViewModel(options, contactRepository, scheduleRemoveTask) as T
}