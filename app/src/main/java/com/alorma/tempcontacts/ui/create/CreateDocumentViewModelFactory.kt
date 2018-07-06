package com.alorma.tempcontacts.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alorma.tempcontacts.domain.repository.ContactRepository
import com.alorma.tempcontacts.domain.work.RemoveContactTask
import javax.inject.Inject

class CreateDocumentViewModelFactory @Inject constructor(
        private val options: CreateDocumentMapper,
        private val contactRepository: ContactRepository,
        private val scheduleRemoveTask: RemoveContactTask
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            CreateDocumentViewModel(options, contactRepository, scheduleRemoveTask) as T
}