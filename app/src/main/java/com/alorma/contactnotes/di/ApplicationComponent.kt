package com.alorma.contactnotes.di

import com.alorma.contactnotes.ui.main.MainComponent
import com.alorma.contactnotes.ui.main.MainModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    infix fun add(screenModule: MainModule): MainComponent
}