package com.alorma.contactnotes.dsl

import com.karumi.dexter.DexterBuilder
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

@DslMarker
annotation class PermissionDsl

@PermissionDsl
class PermissionBuilder(
        private val name: String,
        private val permissionListener: DexterBuilder.Permission
) {

    private lateinit var permissionGranted: (permissionName: String) -> Unit

    private var permissionRationale: ((permissionName: String,
                                       accept: () -> Unit,
                                       cancel: () -> Unit) -> Unit)? = null

    private lateinit var permissionDenied: (permissionName: String) -> Unit

    private lateinit var dexter: DexterBuilder

    fun build(): PermissionBuilder {
        dexter = permissionListener.withPermission(name)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse) {
                        permissionGranted(response.permissionName)
                    }

                    override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest,
                                                                    token: PermissionToken) {

                        permissionRationale?.invoke(permission.name, {
                            token.continuePermissionRequest()
                        }, {
                            token.cancelPermissionRequest()
                        })
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse) {
                        permissionDenied(response.permissionName)
                    }
                })
        return this
    }

    fun onGranted(function: (String) -> Unit) {
        this.permissionGranted = function
    }

    fun onDenied(function: (String) -> Unit) {
        this.permissionDenied = function
    }

    fun rationale(function: (permissionName: String,
                             accept: () -> Unit,
                             cancel: () -> Unit) -> Unit) {
        this.permissionRationale = function
    }

    fun check() {
        dexter.check()
    }
}

@PermissionDsl
fun DexterBuilder.Permission.dsl(
        name: String,
        function: PermissionBuilder.() -> Unit):
        PermissionBuilder =
        with(PermissionBuilder(name, this)) {
            apply(function)
            build()
        }