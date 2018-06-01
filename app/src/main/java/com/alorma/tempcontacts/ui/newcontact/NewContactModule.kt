package com.alorma.tempcontacts.ui.newcontact

import androidx.fragment.app.FragmentActivity
import com.alorma.tempcontacts.di.ScreenModule
import dagger.Module

@Module
class NewContactModule(private val activity: FragmentActivity) : ScreenModule(activity) {

}