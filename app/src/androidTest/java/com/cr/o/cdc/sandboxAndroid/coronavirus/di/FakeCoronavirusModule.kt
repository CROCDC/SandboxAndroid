package com.cr.o.cdc.sandboxAndroid.coronavirus.di

import com.cr.o.cdc.sandboxAndroid.coronavirus.fake.FakeCoronavirusService
import com.cr.o.cdc.sandboxAndroid.coronavirus.repos.CoronavirusService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object FakeCoronavirusModule {

    @Provides
    fun provideCoronavirusService(): CoronavirusService = FakeCoronavirusService()
}
