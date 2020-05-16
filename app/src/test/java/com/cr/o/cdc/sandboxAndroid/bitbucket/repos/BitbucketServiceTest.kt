package com.cr.o.cdc.sandboxAndroid.bitbucket.repos

import com.cr.o.cdc.networking.LiveDataCallAdapterFactory
import com.cr.o.cdc.networking.SuccessResponse
import com.cr.o.cdc.sandboxAndroid.BuildConfig
import com.cr.o.cdc.sandboxAndroid.utils.EndpointTest
import com.cr.o.cdc.sharedtest.getValue
import junit.framework.TestCase.assertTrue
import okhttp3.Credentials
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BitbucketServiceTest : EndpointTest() {

    private val service: BitbucketService = Retrofit.Builder()
        .baseUrl(BuildConfig.URL_BITBUCKET)
        .client(OkHttpClient.Builder().addInterceptor { chain ->
            chain.proceed(
                chain.request().newBuilder().header(
                    "Authorization",
                    Credentials.basic("HsRFzLz7NGbaYg2YRs", "AccxNmY67CApPPYQ3zV7SF46vU6bYN6J")
                ).build()
            )
        }.build())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .build().create(BitbucketService::class.java)


    @Test
    fun login() {
        val response = getValue(
            service.login(
                MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("username", "CajaDeArenaTests")
                    .addFormDataPart("password", "Monroe4500")
                    .addFormDataPart("grant_type", "password")
                    .build()
            )
        )

        assertTrue(response is SuccessResponse)
    }
}