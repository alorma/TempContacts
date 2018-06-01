package com.alorma.tempcontacts.ui.main

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.alorma.tempcontacts.di.ScreenModule
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val activity: FragmentActivity) : ScreenModule(activity) {

    @Provides
    fun provideNavigator(): MainNavigation = MainNavigation(activity)

    @Provides
    fun provideViewModel(factory: MainViewModelFactory): MainViewModel =
            ViewModelProviders.of(activity, factory).get(MainViewModel::class.java)

}