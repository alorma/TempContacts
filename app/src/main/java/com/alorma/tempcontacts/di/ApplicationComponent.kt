package com.alorma.tempcontacts.di

import com.alorma.tempcontacts.domain.work.DeleteSingleContactWorker
import com.alorma.tempcontacts.domain.work.SyncContactsWorker
import com.alorma.tempcontacts.ui.configuration.ConfigDocumentComponent
import com.alorma.tempcontacts.ui.configuration.ConfigDocumentModule
import com.alorma.tempcontacts.ui.documents.DocumentsListComponent
import com.alorma.tempcontacts.ui.documents.DocumentsListModule
import com.alorma.tempcontacts.ui.select.SelectDocumentComponent
import com.alorma.tempcontacts.ui.select.SelectDocumentModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, DataModule::class])
interface ApplicationComponent {
    infix fun add(documentsModule: DocumentsListModule): DocumentsListComponent
    infix fun add(selectDocumentModule: SelectDocumentModule): SelectDocumentComponent
    infix fun add(configDocumentModule: ConfigDocumentModule): ConfigDocumentComponent

    infix fun inject(deleteSingleContactWorker: DeleteSingleContactWorker)
    infix fun inject(syncContactsWorker: SyncContactsWorker)
}