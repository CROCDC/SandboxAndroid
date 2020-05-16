package com.cr.o.cdc.sandboxAndroid.bitbucket.di

import com.cr.o.cdc.networking.LiveDataCallAdapterFactory
import com.cr.o.cdc.sandboxAndroid.BuildConfig
import com.cr.o.cdc.sandboxAndroid.bitbucket.repos.BitbucketAuthService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModuleBitbucket {

    @Provides
    @Singleton
    fun provideBitbucketAuthService(
        retrofitBuilder: Retrofit.Builder
    ): BitbucketAuthService = retrofitBuilder.baseUrl(BuildConfig.URL_BITBUCKET_AUTH)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .build().create(BitbucketAuthService::class.java)

}