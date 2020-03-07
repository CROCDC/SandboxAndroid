package com.cr.o.cdc.sandboxAndroid.di

import com.cr.o.cdc.sandboxAndroid.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}