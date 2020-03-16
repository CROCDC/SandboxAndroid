package com.cr.o.cdc.sandboxAndroid.pagination.di

import com.cr.o.cdc.sandboxAndroid.pagination.fragments.RecipesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModulePagination {

    @ContributesAndroidInjector
    abstract fun contributesRecipesFragment(): RecipesFragment

}