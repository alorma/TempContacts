package com.alorma.contactnotes.ui.main

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alorma.contactnotes.dsl.PermissionBuilder
import com.alorma.contactnotes.dsl.dsl
import com.karumi.dexter.DexterBuilder

class MainViewModel(private val permission: DexterBuilder.Permission,
                    private val navigation: MainNavigation) : ViewModel() {

    val contactUri: MutableLiveData<Uri> = MutableLiveData()

    private lateinit var permissionBuilder: PermissionBuilder

    fun setupPermission(onDeny: () -> Unit, rational: (String) -> Boolean = { true }) {
        permissionBuilder = permission.dsl(Manifest.permission.READ_CONTACTS) {
            onGranted {
                navigation.openContacts()
            }
            onDenied { onDeny() }
            rationale { permissionName, accept, cancel ->
                if (rational(permissionName)) {
                    accept()
                } else {
                    cancel()
                }
            }
        }
    }

    fun load(): LiveData<Uri> {
        permissionBuilder.check()
        return contactUri
    }

    fun onResult(data: Intent?) {
        navigation.parse(data)?.let {
            contactUri.postValue(it)
        }
    }
}
