package com.alorma.tempcontacts.di

import androidx.fragment.app.FragmentActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.DexterBuilder
import dagger.Module
import dagger.Provides

@Module
open class ScreenModule(private val activity: FragmentActivity) {

    @Provides
    fun provideActivity(): FragmentActivity = activity

    @Provides
    fun providesPermission(): DexterBuilder.Permission = Dexter.withActivity(activity)
}