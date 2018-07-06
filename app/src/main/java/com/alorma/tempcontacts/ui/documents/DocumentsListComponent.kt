package com.alorma.tempcontacts.ui.documents

import dagger.Subcomponent

@Subcomponent(modules = [DocumentsListModule::class])
interface DocumentsListComponent {
    infix fun inject(documentsFragment: DocumentsFragment)
}