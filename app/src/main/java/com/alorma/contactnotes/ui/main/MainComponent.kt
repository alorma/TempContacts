package com.alorma.contactnotes.ui.main

import com.alorma.contactnotes.di.ScreenModule
import com.karumi.dexter.DexterBuilder
import dagger.Subcomponent
import javax.inject.Named


@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    infix fun inject(mainFragment: MainFragment)

    @Named(ScreenModule.READ_CONTACT_PERMISSION)
    fun provideReadContacts(): DexterBuilder.SinglePermissionListener
}