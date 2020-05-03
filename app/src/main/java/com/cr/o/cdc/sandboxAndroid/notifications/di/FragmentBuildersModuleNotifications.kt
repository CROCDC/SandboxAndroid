package com.cr.o.cdc.sandboxAndroid.notifications.di

import com.cr.o.cdc.sandboxAndroid.notifications.fragments.NotificationsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModuleNotifications {

    @ContributesAndroidInjector
    abstract fun contributesNotificationsFragment(): NotificationsFragment
}
