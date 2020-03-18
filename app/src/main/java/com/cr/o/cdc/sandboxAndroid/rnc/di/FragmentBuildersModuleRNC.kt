package com.cr.o.cdc.sandboxAndroid.rnc.di

import com.cr.o.cdc.sandboxAndroid.rnc.fragments.RNCFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModuleRNC {

    @ContributesAndroidInjector
    abstract fun contributesRNCFragment(): RNCFragment

}