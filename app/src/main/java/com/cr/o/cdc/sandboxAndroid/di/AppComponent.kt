package com.cr.o.cdc.sandboxAndroid.di

import com.cr.o.cdc.sandboxAndroid.SandBoxApp
import com.cr.o.cdc.sandboxAndroid.bitbucket.di.AppModuleBitbucket
import com.cr.o.cdc.sandboxAndroid.bitbucket.di.FragmentBuildersModuleBitbucket
import com.cr.o.cdc.sandboxAndroid.coronavirus.di.FragmentBuildersModuleCoronavirus
import com.cr.o.cdc.sandboxAndroid.downdetector.di.FragmentBuildersModuleDownDetector
import com.cr.o.cdc.sandboxAndroid.notifications.di.FragmentBuildersModuleNotifications
import com.cr.o.cdc.sandboxAndroid.pagination.di.FragmentBuildersModulePagination
import com.cr.o.cdc.sandboxAndroid.pokedex.di.FragmentBuildersModulePokemons
import com.cr.o.cdc.sandboxAndroid.rnc.di.FragmentBuildersModuleRNC
import com.cr.o.cdc.sandboxAndroid.whatsapputils.di.FragmentBuildersModuleWhatsappUtils
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
        AppModuleBitbucket::class,
        ServiceModule::class,
        WorkerModule::class,
        ViewModelModule::class,
        FragmentBuildersModuleBitbucket::class,
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
