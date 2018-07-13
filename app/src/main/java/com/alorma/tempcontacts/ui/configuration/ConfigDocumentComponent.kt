package com.alorma.tempcontacts.ui.configuration

import dagger.Subcomponent

@Subcomponent(modules = [ConfigDocumentModule::class])
interface ConfigDocumentComponent {
    infix fun inject(configurationFragment: DocumentConfigurationFragment)
}