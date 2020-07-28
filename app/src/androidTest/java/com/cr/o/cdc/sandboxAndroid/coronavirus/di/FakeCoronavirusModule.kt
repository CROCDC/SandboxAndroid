package com.cr.o.cdc.sandboxAndroid.coronavirus.di

import com.cr.o.cdc.sandboxAndroid.coronavirus.repos.CoronavirusService
import com.cr.o.cdc.sandboxAndroid.coronavirus.fake.FakeCoronavirusService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
object FakeCoronavirusModule {

    @Provides
    fun provideCoronavirusService(): CoronavirusService = FakeCoronavirusService()
}
