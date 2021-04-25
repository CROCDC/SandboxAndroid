package com.cr.o.cdc.sandboxAndroid.di

import com.cr.o.cdc.sandboxAndroid.coronavirus.di.AppModule
import com.cr.o.cdc.sandboxAndroid.coronavirus.di.CoronavirusModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Cami on 4/24/21.
 */
@Singleton
@Component(modules = [AppModule::class, CoronavirusModule::class])
interface ApplicationComponent