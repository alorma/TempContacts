package com.alorma.tempcontacts.ui.common

import android.content.Intent
import androidx.fragment.app.FragmentActivity

open class Navigator(private val activity: FragmentActivity) {

    fun start(intent: Intent) = activity.startActivity(intent)

    fun startForResult(intent: Intent, requestCode: Int) =
            activity.startActivityForResult(intent, requestCode)
}