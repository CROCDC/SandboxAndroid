package com.cr.o.cdc.sandboxAndroid.bitbucket.di

import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.bitbucket.vm.WorkspacesViewModel
import com.cr.o.cdc.sandboxAndroid.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModuleBitbucket {

    @Binds
    @IntoMap
    @ViewModelKey(WorkspacesViewModel::class)
    abstract fun bindWorkspacesViewModel(workspacesViewModel: WorkspacesViewModel): ViewModel

}
