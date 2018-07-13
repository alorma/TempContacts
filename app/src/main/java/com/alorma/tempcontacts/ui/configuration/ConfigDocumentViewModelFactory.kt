package com.alorma.tempcontacts.ui.configuration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alorma.tempcontacts.domain.repository.DocumentsRepository
import com.alorma.tempcontacts.domain.work.RemoveContactTask
import javax.inject.Inject

class ConfigDocumentViewModelFactory @Inject constructor(
        private val options: ConfigDocumentMapper,
        private val documentsRepository: DocumentsRepository,
        private val scheduleRemoveTask: RemoveContactTask
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            ConfigDocumentViewModel(options, documentsRepository, scheduleRemoveTask) as T
}