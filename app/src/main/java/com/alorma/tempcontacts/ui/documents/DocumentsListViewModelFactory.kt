package com.alorma.tempcontacts.ui.documents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alorma.tempcontacts.domain.repository.ContactRepository
import com.alorma.tempcontacts.domain.work.CheckRemovedContactsTask
import javax.inject.Inject

class DocumentsListViewModelFactory @Inject constructor(
        private val operations: DocumentsListMapper,
        private val contactRepository: ContactRepository,
        private val tasker: CheckRemovedContactsTask
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            DocumentsListViewModel(operations, contactRepository, tasker) as T
}