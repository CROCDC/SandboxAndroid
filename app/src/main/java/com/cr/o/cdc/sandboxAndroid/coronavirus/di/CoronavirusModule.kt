package com.cr.o.cdc.sandboxAndroid.coronavirus.di

import android.content.Context
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.coronavirus.repos.CoronavirusService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class CoronavirusModule {

    @Singleton
    @Provides
    fun provideCoronavirusService(
        retrofitBuilder: Retrofit.Builder,
        @ApplicationContext appContext: Context
    ): CoronavirusService =
        retrofitBuilder.baseUrl(appContext.resources.getString(R.string.coronavirus_api)).build()
            .create(CoronavirusService::class.java)

}