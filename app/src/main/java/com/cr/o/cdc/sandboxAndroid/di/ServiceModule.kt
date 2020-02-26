package com.cr.o.cdc.sandboxAndroid.di

import com.cr.o.cdc.sandboxAndroid.notifications.util.MyFirebaseMessagingService
import com.cr.o.cdc.sandboxAndroid.services.MyNotificationListenerService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServiceModule {

    @ContributesAndroidInjector
    abstract fun contributeMyFirebaseMessagingService(): MyFirebaseMessagingService

    @ContributesAndroidInjector
    abstract fun contributeMyNotificationListenerService(): MyNotificationListenerService
}
