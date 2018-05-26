package com.alorma.contactnotes

import android.app.Application
import com.alorma.contactnotes.di.ApplicationComponent
import com.alorma.contactnotes.di.ApplicationModule
import com.alorma.contactnotes.di.DaggerApplicationComponent

class ContactNotesApp : Application() {

    companion object {
        lateinit var component: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

}