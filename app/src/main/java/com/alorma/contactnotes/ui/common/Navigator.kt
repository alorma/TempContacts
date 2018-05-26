package com.alorma.contactnotes.ui.common

import android.content.Intent
import androidx.fragment.app.Fragment

open class Navigator(private val fragment: Fragment) {

    fun start(intent: Intent) = fragment.startActivity(intent)

    fun startForResult(intent: Intent, requestCode: Int) =
            fragment.startActivityForResult(intent, requestCode)

}