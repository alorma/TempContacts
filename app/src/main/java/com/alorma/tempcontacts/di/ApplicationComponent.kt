package com.alorma.tempcontacts.di

import com.alorma.tempcontacts.domain.work.DeleteSingleContactWorker
import com.alorma.tempcontacts.domain.work.SyncContactsWorker
import com.alorma.tempcontacts.ui.documents.DocumentsListComponent
import com.alorma.tempcontacts.ui.documents.DocumentsListModule
import com.alorma.tempcontacts.ui.create.CreateDocumentComponent
import com.alorma.tempcontacts.ui.create.CreateDocumentModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, DataModule::class])
interface ApplicationComponent {
    infix fun add(documentsModule: DocumentsListModule): DocumentsListComponent
    infix fun add(createDocumentModule: CreateDocumentModule): CreateDocumentComponent

    infix fun inject(deleteSingleContactWorker: DeleteSingleContactWorker)
    infix fun inject(syncContactsWorker: SyncContactsWorker)
}