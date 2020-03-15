package com.cr.o.cdc.sandboxAndroid.coronavirus.di

import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.coronavirus.vm.CoronavirusViewModel
import com.cr.o.cdc.sandboxAndroid.coronavirus.vm.SearchViewModel
import com.cr.o.cdc.sandboxAndroid.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModuleCoronavirus {

    @Binds
    @IntoMap
    @ViewModelKey(CoronavirusViewModel::class)
    abstract fun bindCoronavirusViewModel(coronavirusViewModel: CoronavirusViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

}