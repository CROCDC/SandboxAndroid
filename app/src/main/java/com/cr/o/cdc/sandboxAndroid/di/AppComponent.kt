package com.cr.o.cdc.sandboxAndroid.di

import com.cr.o.cdc.sandboxAndroid.SandBoxApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ServiceModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        FragmentBuildersModule::class]
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