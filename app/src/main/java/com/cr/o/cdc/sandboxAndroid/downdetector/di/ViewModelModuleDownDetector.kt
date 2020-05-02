package com.cr.o.cdc.sandboxAndroid.downdetector.di

import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.di.ViewModelKey
import com.cr.o.cdc.sandboxAndroid.downdetector.vm.AddSiteViewModel
import com.cr.o.cdc.sandboxAndroid.downdetector.vm.SitesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModuleDownDetector {

    @Binds
    @IntoMap
    @ViewModelKey(SitesListViewModel::class)
    abstract fun bindSitesListViewModel(sitesListViewModel: SitesListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddSiteViewModel::class)
    abstract fun bindAddSiteViewModel(addSiteViewModel: AddSiteViewModel): ViewModel
}