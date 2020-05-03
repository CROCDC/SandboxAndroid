package com.cr.o.cdc.sandboxAndroid.di

import androidx.lifecycle.ViewModelProvider
import com.cr.o.cdc.sandboxAndroid.coronavirus.di.ViewModelModuleCoronavirus
import com.cr.o.cdc.sandboxAndroid.downdetector.di.ViewModelModuleDownDetector
import com.cr.o.cdc.sandboxAndroid.notifications.di.ViewModelModuleNotifications
import com.cr.o.cdc.sandboxAndroid.pagination.di.ViewModelModulePagination
import com.cr.o.cdc.sandboxAndroid.pokedex.di.ViewModelModulePokemons
import com.cr.o.cdc.sandboxAndroid.rnc.di.ViewModelModuleRNC
import com.cr.o.cdc.sandboxAndroid.whatsapputils.di.ViewModelModuleWhatsappUtils
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        ViewModelModuleCoronavirus::class,
        ViewModelModuleWhatsappUtils::class,
        ViewModelModuleNotifications::class,
        ViewModelModulePagination::class,
        ViewModelModulePokemons::class,
        ViewModelModuleRNC::class,
        ViewModelModuleDownDetector::class
    ]
)
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
