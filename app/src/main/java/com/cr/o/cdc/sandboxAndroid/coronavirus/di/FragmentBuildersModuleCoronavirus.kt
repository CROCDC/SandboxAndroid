package com.cr.o.cdc.sandboxAndroid.coronavirus.di

import com.cr.o.cdc.sandboxAndroid.coronavirus.fragments.CoronavirusFragment
import com.cr.o.cdc.sandboxAndroid.coronavirus.fragments.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModuleCoronavirus {

    @ContributesAndroidInjector
    abstract fun contributesCoronavirusFragment(): CoronavirusFragment

    @ContributesAndroidInjector
    abstract fun contributesSearchFragment(): SearchFragment
}