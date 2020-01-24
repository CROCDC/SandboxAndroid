package com.cr.o.cdc.sandboxAndroid.di

import com.cr.o.cdc.sandboxAndroid.push.MyFirebaseMessagingService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServiceModule {

    @ContributesAndroidInjector
    abstract fun contributeMyFirebaseMessagingService(): MyFirebaseMessagingService
}
