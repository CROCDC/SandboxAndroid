package com.cr.o.cdc.sandboxAndroid.coronavirus.di

import com.cr.o.cdc.sandboxAndroid.coronavirus.fake.FakeCoronavirusService
import com.cr.o.cdc.sandboxAndroid.coronavirus.repos.CoronavirusService
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CoronavirusModule::class]
)
object FakeCoronavirusModule {

    @Provides
    fun provideCoronavirusService(): CoronavirusService = FakeCoronavirusService()
}
