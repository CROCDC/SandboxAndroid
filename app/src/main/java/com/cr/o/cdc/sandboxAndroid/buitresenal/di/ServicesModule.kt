package com.cr.o.cdc.sandboxAndroid.buitresenal.di

import com.cr.o.cdc.sandboxAndroid.buitresenal.repos.ApiInstagramService
import com.cr.o.cdc.sandboxAndroid.buitresenal.repos.GraphInstagramService
import com.cr.o.cdc.sandboxAndroid.buitresenal.util.InstagramApiInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Cami on 5/22/21.
 */
@Module
@InstallIn(SingletonComponent::class)
class ServicesModule {

    @Provides
    @Singleton
    fun provideInstagramService(retrofit: Retrofit.Builder): ApiInstagramService =
        retrofit.baseUrl(InstagramApiInfo.API_URL).build().create(ApiInstagramService::class.java)

    @Provides
    @Singleton
    fun provideGraphInstagramService(retrofit: Retrofit.Builder): GraphInstagramService =
        retrofit.baseUrl(InstagramApiInfo.GRAPH_URL).build().create(
            GraphInstagramService::class.java
        )
}
