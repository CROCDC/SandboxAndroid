package com.cr.o.cdc.sandboxAndroid.di

import com.cr.o.cdc.sandboxAndroid.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Camilo on 31/12/19.
 */
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}