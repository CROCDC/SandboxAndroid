package com.cr.o.cdc.sandboxAndroid.downdetector.di

import com.cr.o.cdc.sandboxAndroid.downdetector.fragments.AddSiteBottomDialog
import com.cr.o.cdc.sandboxAndroid.downdetector.fragments.SitesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModuleDownDetector {

    @ContributesAndroidInjector
    abstract fun contributesSitesListFragment(): SitesListFragment

    @ContributesAndroidInjector
    abstract fun contributesAddSiteBottomDialog(): AddSiteBottomDialog
}
