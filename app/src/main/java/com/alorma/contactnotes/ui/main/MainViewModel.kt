package com.alorma.contactnotes.ui.main

import androidx.lifecycle.ViewModel
import com.alorma.contactnotes.dsl.PermissionBuilder
import com.alorma.contactnotes.dsl.dsl
import com.karumi.dexter.DexterBuilder

class MainViewModel(
        private val permission: DexterBuilder.SinglePermissionListener) : ViewModel() {

    private lateinit var permissionBuilder: PermissionBuilder

    fun setupPermission(onGrant: () -> Unit, onDeny: () -> Unit, rational: (String) -> Boolean = { true }) {
        permissionBuilder = permission.dsl {
            onGranted { onGrant() }
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

    fun load() {
        permissionBuilder.check()
    }
}
