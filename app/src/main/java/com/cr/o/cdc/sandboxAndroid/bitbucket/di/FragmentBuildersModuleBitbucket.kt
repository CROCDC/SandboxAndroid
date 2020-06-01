package com.cr.o.cdc.sandboxAndroid.bitbucket.di

import com.cr.o.cdc.sandboxAndroid.bitbucket.ui.LoginFragment
import com.cr.o.cdc.sandboxAndroid.bitbucket.ui.WorkspacesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModuleBitbucket {

    @ContributesAndroidInjector
    abstract fun contributesWorkspacesFragment(): WorkspacesFragment

    @ContributesAndroidInjector
    abstract fun contributesLoginFragment(): LoginFragment

}
