package com.alorma.contactnotes.ui.main

import android.Manifest
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alorma.contactnotes.data.framework.SystemContactDataSource
import com.alorma.contactnotes.domain.model.Contact
import com.alorma.contactnotes.dsl.PermissionBuilder
import com.alorma.contactnotes.dsl.dsl
import com.karumi.dexter.DexterBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val permission: DexterBuilder.Permission,
                    private val navigation: MainNavigation,
                    private val systemDs: SystemContactDataSource) : ViewModel() {

    private lateinit var contactUri: MutableLiveData<Contact>

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

    fun load(): LiveData<Contact> {
        contactUri = MutableLiveData()
        permissionBuilder.check()
        return contactUri
    }

    fun onResult(data: Intent?) {
        navigation.parse(data)?.let {
            systemDs(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        contactUri.postValue(it)
                    }, {

                    })
        }
    }
}
