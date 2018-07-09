package com.alorma.tempcontacts.ui.configuration

import com.alorma.tempcontacts.ui.select.SelectDocumentFragment
import dagger.Subcomponent

@Subcomponent(modules = [CreateDocumentModule::class])
interface CreateDocumentComponent {
    infix fun inject(selectDocumentFragment: SelectDocumentFragment)
}