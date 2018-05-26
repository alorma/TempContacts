package com.alorma.contactnotes.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.alorma.contactnotes.di.ScreenModule
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val activity: FragmentActivity,
                 private val fragment: Fragment) : ScreenModule(activity) {

    @Provides
    fun provideNavigator(): MainNavigation = MainNavigation(fragment)

    @Provides
    fun provideViewModel(factory: MainViewModelFactory): MainViewModel =
            ViewModelProviders.of(activity, factory).get(MainViewModel::class.java)

}