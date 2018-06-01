package com.alorma.tempcontacts.ui.newcontact

import dagger.Subcomponent

@Subcomponent(modules = [NewContactModule::class])
interface NewContactComponent {
    infix fun inject(newContactActivity: NewContactActivity)
}