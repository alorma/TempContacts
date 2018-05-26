package com.alorma.contactnotes.ui.main

import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    infix fun inject(mainFragment: MainFragment)
}