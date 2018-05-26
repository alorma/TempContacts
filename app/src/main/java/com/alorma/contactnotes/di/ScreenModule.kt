package com.alorma.contactnotes.di

import android.Manifest
import android.app.Activity
import com.karumi.dexter.Dexter
import com.karumi.dexter.DexterBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
open class ScreenModule(private val activity: Activity?) {

    companion object {
        const val READ_CONTACT_PERMISSION = "READ_CONTACT_PERMISSION"
    }

    @Provides
    fun providesContactsPermission(): DexterBuilder.Permission = Dexter.withActivity(activity)

    @Provides
    @Named(READ_CONTACT_PERMISSION)
    fun provideContactsPermissions(builder: DexterBuilder.Permission):
            DexterBuilder.SinglePermissionListener =
            providePermission(Manifest.permission.READ_CONTACTS, builder)

    private fun providePermission(name: String, builder: DexterBuilder.Permission):
            DexterBuilder.SinglePermissionListener =
            builder.withPermission(name)
}