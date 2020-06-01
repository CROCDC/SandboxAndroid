package com.cr.o.cdc.sandboxAndroid.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cr.o.cdc.sandboxAndroid.HomeViewModel
import com.cr.o.cdc.sandboxAndroid.bitbucket.di.ViewModelModuleBitbucket
import com.cr.o.cdc.sandboxAndroid.coronavirus.di.ViewModelModuleCoronavirus
import com.cr.o.cdc.sandboxAndroid.downdetector.di.ViewModelModuleDownDetector
import com.cr.o.cdc.sandboxAndroid.notifications.di.ViewModelModuleNotifications
import com.cr.o.cdc.sandboxAndroid.pagination.di.ViewModelModulePagination
import com.cr.o.cdc.sandboxAndroid.pokedex.di.ViewModelModulePokemons
import com.cr.o.cdc.sandboxAndroid.rnc.di.ViewModelModuleRNC
import com.cr.o.cdc.sandboxAndroid.whatsapputils.di.ViewModelModuleWhatsappUtils
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ViewModelModuleCoronavirus::class,
        ViewModelModuleWhatsappUtils::class,
        ViewModelModuleNotifications::class,
        ViewModelModulePagination::class,
        ViewModelModulePokemons::class,
        ViewModelModuleRNC::class,
        ViewModelModuleDownDetector::class,
        ViewModelModuleBitbucket::class
    ]
)
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
