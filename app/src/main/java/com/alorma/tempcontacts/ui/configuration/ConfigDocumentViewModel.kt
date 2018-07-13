package com.alorma.tempcontacts.ui.configuration

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alorma.tempcontacts.domain.model.AppDocument
import com.alorma.tempcontacts.domain.model.NewDocument
import com.alorma.tempcontacts.domain.repository.DocumentsRepository
import com.alorma.tempcontacts.domain.work.RemoveContactTask
import com.alorma.tempcontacts.extensions.actionSwitchMap
import com.alorma.tempcontacts.extensions.switchMap
import com.alorma.tempcontacts.ui.common.BaseViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class ConfigDocumentViewModel @Inject constructor(
        private val options: ConfigDocumentMapper,
        private val documentsRepository: DocumentsRepository,
        private val scheduleRemoveTask: RemoveContactTask) :
        BaseViewModel() {

    private val saveLiveData: MutableLiveData<Save> = MutableLiveData()

    private val saveContact: LiveData<ConfigDocumentMapper.NewState> = saveLiveData.actionSwitchMap {
        when (it) {
            Save.InvalidTime -> options.invalidTime()
            is Save.SaveContact -> {
                val timeCalculation = getTime(it.time)
                val newDocument = NewDocument(it.appDocument.androidId, it.appDocument.name,
                        it.appDocument.type, timeCalculation)
                documentsRepository.create(newDocument)
                schedule(it.appDocument.androidId, timeCalculation)
                options.saveComplete()
            }
        }
    }

    private val uriLiveData: MutableLiveData<Uri> = MutableLiveData()
    private val getAppDocumentLiveData: LiveData<AppDocument?> = uriLiveData.switchMap {
        documentsRepository.import(it)
    }

    fun onDocumentUriLoaded(uri: Uri): LiveData<AppDocument?> {
        uriLiveData.postValue(uri)
        return getAppDocumentLiveData
    }

    fun save(appDocument: AppDocument, time: TimeSelection): LiveData<ConfigDocumentMapper.NewState> {
        val saveState = if (time == TimeSelection.NONE) {
            Save.InvalidTime
        } else {
            Save.SaveContact(appDocument, time)
        }
        saveLiveData.postValue(saveState)

        return saveContact
    }

    private fun getTime(time: TimeSelection): Long = when (time) {
        TimeSelection.HOUR -> TimeUnit.HOURS.toMillis(1)
        TimeSelection.DAY -> TimeUnit.DAYS.toMillis(1)
        TimeSelection.WEEK -> TimeUnit.DAYS.toMillis(7)
        TimeSelection.MONTH -> TimeUnit.DAYS.toMillis(30)
        is TimeSelection.Custom -> time.unit.toMillis(time.number)
        else -> 0
    }

    private fun schedule(androidId: String, time: Long) =
            scheduleRemoveTask.removeUser(androidId, time)

    sealed class Save {
        object InvalidTime : Save()
        data class SaveContact(val appDocument: AppDocument, val time: TimeSelection) : Save()
    }
}