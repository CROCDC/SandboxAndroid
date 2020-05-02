package com.cr.o.cdc.sandboxAndroid.di

import com.cr.o.cdc.sandboxAndroid.SandBoxApp
import com.cr.o.cdc.sandboxAndroid.coronavirus.di.FragmentBuildersModuleCoronavirus
import com.cr.o.cdc.sandboxAndroid.coronavirus.di.ViewModelModuleCoronavirus
import com.cr.o.cdc.sandboxAndroid.downdetector.di.FragmentBuildersModuleDownDetector
import com.cr.o.cdc.sandboxAndroid.downdetector.di.ViewModelModuleDownDetector
import com.cr.o.cdc.sandboxAndroid.notifications.di.FragmentBuildersModuleNotifications
import com.cr.o.cdc.sandboxAndroid.notifications.di.ViewModelModuleNotifications
import com.cr.o.cdc.sandboxAndroid.pagination.di.FragmentBuildersModulePagination
import com.cr.o.cdc.sandboxAndroid.pagination.di.ViewModelModulePagination
import com.cr.o.cdc.sandboxAndroid.pokedex.di.FragmentBuildersModulePokemons
import com.cr.o.cdc.sandboxAndroid.pokedex.di.ViewModelModulePokemons
import com.cr.o.cdc.sandboxAndroid.rnc.di.FragmentBuildersModuleRNC
import com.cr.o.cdc.sandboxAndroid.rnc.di.ViewModelModuleRNC
import com.cr.o.cdc.sandboxAndroid.whatsapputils.di.FragmentBuildersModuleWhatsappUtils
import com.cr.o.cdc.sandboxAndroid.whatsapputils.di.ViewModelModuleWhatsappUtils
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityModule::class,
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ServiceModule::class,
        ViewModelModule::class,
        ViewModelModuleCoronavirus::class,
        ViewModelModuleWhatsappUtils::class,
        ViewModelModuleNotifications::class,
        ViewModelModulePagination::class,
        ViewModelModulePokemons::class,
        ViewModelModuleRNC::class,
        ViewModelModuleDownDetector::class,
        FragmentBuildersModuleCoronavirus::class,
        FragmentBuildersModuleWhatsappUtils::class,
        FragmentBuildersModuleNotifications::class,
        FragmentBuildersModulePagination::class,
        FragmentBuildersModulePokemons::class,
        FragmentBuildersModuleRNC::class,
        FragmentBuildersModuleDownDetector::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: SandBoxApp): Builder

        fun build(): AppComponent
    }

    fun inject(app: SandBoxApp)
}