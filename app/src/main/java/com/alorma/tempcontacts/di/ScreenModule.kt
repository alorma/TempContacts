package com.alorma.tempcontacts.di

import androidx.fragment.app.Fragment
import com.karumi.dexter.Dexter
import com.karumi.dexter.DexterBuilder
import dagger.Module
import dagger.Provides

@Module
open class ScreenModule(private val fragment: Fragment) {

    @Provides
    fun providesPermission(): DexterBuilder.Permission = Dexter.withActivity(fragment.activity)
}