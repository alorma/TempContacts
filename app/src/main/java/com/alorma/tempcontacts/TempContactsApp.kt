package com.alorma.tempcontacts

import android.app.Application
import com.alorma.tempcontacts.di.ApplicationComponent
import com.alorma.tempcontacts.di.ApplicationModule
import com.alorma.tempcontacts.di.DaggerApplicationComponent
import com.alorma.tempcontacts.di.DataModule
import com.jakewharton.threetenabp.AndroidThreeTen

class TempContactsApp : Application() {

    companion object {
        lateinit var component: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)

        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .dataModule(DataModule(this))
                .build()
    }

}