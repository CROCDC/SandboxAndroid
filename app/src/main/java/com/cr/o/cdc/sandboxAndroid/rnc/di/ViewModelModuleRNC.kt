package com.cr.o.cdc.sandboxAndroid.rnc.di

import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.di.ViewModelKey
import com.cr.o.cdc.sandboxAndroid.rnc.vm.RNCViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModuleRNC {

    @Binds
    @IntoMap
    @ViewModelKey(RNCViewModel::class)
    abstract fun bindRNCViewModel(RNCViewModel: RNCViewModel): ViewModel
}
