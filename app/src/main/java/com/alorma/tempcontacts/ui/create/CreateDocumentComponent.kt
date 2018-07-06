package com.alorma.tempcontacts.ui.create

import dagger.Subcomponent

@Subcomponent(modules = [CreateDocumentModule::class])
interface CreateDocumentComponent {
    infix fun inject(createDocumentFragment: CreateDocumentFragment)
}