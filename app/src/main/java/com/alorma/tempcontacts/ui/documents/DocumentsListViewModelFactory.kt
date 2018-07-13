package com.alorma.tempcontacts.ui.documents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alorma.tempcontacts.domain.repository.DocumentsRepository
import com.alorma.tempcontacts.domain.work.CheckRemovedContactsTask
import javax.inject.Inject

class DocumentsListViewModelFactory @Inject constructor(
        private val operations: DocumentsListMapper,
        private val documentsRepository: DocumentsRepository,
        private val tasker: CheckRemovedContactsTask
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            DocumentsListViewModel(operations, documentsRepository, tasker) as T
}