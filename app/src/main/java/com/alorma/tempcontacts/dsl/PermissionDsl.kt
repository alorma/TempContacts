package com.alorma.tempcontacts.dsl

import com.karumi.dexter.DexterBuilder
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

@DslMarker
annotation class PermissionDsl

@PermissionDsl
class PermissionBuilder(
        private val permissionListener: DexterBuilder.Permission,
        private val name: Array<String>
) {

    private lateinit var permissionGranted: () -> Unit

    private var permissionRationale: ((permissionName: String,
                                       accept: () -> Unit,
                                       cancel: () -> Unit) -> Unit)? = null

    private lateinit var permissionDenied: (permissionName: Array<String>) -> Unit

    private lateinit var dexter: DexterBuilder

    fun build(): PermissionBuilder {
        dexter = permissionListener.withPermissions(name.toList())
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                        if (report.isAnyPermissionPermanentlyDenied) {
                            permissionDenied(report.deniedPermissionResponses.map { it.permissionName }.toTypedArray())
                        } else if (report.areAllPermissionsGranted()) {
                            permissionGranted()
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(permissions: MutableList<PermissionRequest>,
                                                                    token: PermissionToken) {
                        permissions.forEach {
                            permissionRationale?.invoke(it.name, {
                                token.continuePermissionRequest()
                            }, {
                                token.cancelPermissionRequest()
                            })
                        }
                    }
                })
        return this
    }

    fun onGranted(function: () -> Unit) {
        this.permissionGranted = function
    }

    fun onDenied(function: (Array<String>) -> Unit) {
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
        name: Array<String>,
        function: PermissionBuilder.() -> Unit):
        PermissionBuilder =
        with(PermissionBuilder(this, name)) {
            apply(function)
            build()
        }