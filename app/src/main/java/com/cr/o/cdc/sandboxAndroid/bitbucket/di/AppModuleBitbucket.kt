package com.cr.o.cdc.sandboxAndroid.bitbucket.di

import android.content.SharedPreferences
import com.cr.o.cdc.networking.LiveDataCallAdapterFactory
import com.cr.o.cdc.sandboxAndroid.BuildConfig
import com.cr.o.cdc.sandboxAndroid.bitbucket.repos.BitbucketService
import dagger.Module
import dagger.Provides
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModuleBitbucket {

    // TODO IMPROVE
    @Provides
    @Singleton
    fun provideBitbucketService(
        retrofitBuilder: Retrofit.Builder,
        sharedPreferences: SharedPreferences
    ): BitbucketService = retrofitBuilder.baseUrl(BuildConfig.URL_BITBUCKET)
        .client(
            OkHttpClient.Builder().addInterceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder().header(
                        "Authorization",
                        Credentials.basic(
                            sharedPreferences.getString("key", null) ?: "",
                            sharedPreferences.getString("secret", null) ?: ""
                        )
                    ).build()
                )
            }.build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .build().create(BitbucketService::class.java)


}