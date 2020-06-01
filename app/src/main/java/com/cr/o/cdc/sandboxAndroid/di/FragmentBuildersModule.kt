package com.cr.o.cdc.sandboxAndroid.di

import com.cr.o.cdc.sandboxAndroid.HomeFragment
import com.cr.o.cdc.sandboxAndroid.downdetector.fragments.SitesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributesHomeFragment(): HomeFragment
}