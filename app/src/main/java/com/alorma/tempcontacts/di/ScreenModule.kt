package com.alorma.tempcontacts.di

import android.app.Activity
import com.karumi.dexter.Dexter
import com.karumi.dexter.DexterBuilder
import dagger.Module
import dagger.Provides

@Module
open class ScreenModule(private val activity: Activity?) {

    @Provides
    fun providesPermission(): DexterBuilder.Permission = Dexter.withActivity(activity)
}