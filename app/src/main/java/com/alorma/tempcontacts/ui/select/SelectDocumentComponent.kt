package com.alorma.tempcontacts.ui.select

import dagger.Subcomponent

@Subcomponent(modules = [SelectDocumentModule::class])
interface SelectDocumentComponent {
    infix fun inject(selectDocumentFragment: SelectDocumentFragment)
}