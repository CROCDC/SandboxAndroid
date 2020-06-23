package com.cr.o.cdc.sandboxAndroid.di

import com.cr.o.cdc.sandboxAndroid.coronavirus.repos.CoronavirusService
import com.cr.o.cdc.sandboxAndroid.fake.FakeCoronavirusService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class FakeServiceModule {

    @Singleton
    @Binds
    abstract fun bindCoronavirusService(fakeCoronavirusService: FakeCoronavirusService): CoronavirusService
}
