package com.cr.o.cdc.sandboxAndroid.bitbucket.repos

import com.cr.o.cdc.networking.SuccessResponse
import com.cr.o.cdc.sandboxAndroid.bitbucket.di.AppModuleBitbucket
import com.cr.o.cdc.sandboxAndroid.bitbucket.model.LoginResponse
import okhttp3.MultipartBody
import org.junit.Test
import retrofit2.Retrofit
import java.util.Base64

class BitbucketServiceTest {

    private val service: BitbucketAuthService =
        AppModuleBitbucket().provideBitbucketAuthService(Retrofit.Builder())

    private lateinit var accessToken: String


    init {
        accessToken = (AppModuleBitbucket().provideBitbucketAuthService(Retrofit.Builder()).login(
            "Basic ${Base64.getEncoder()
                .encodeToString("HsRFzLz7NGbaYg2YRs:AccxNmY67CApPPYQ3zV7SF46vU6bYN6J".toByteArray())}",
            MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("username", "CajaDeArenaTests")
                .addFormDataPart("password", "Monroe4500")
                .addFormDataPart("grant_type", "password")
                .build()
        ) as SuccessResponse<LoginResponse>).data?.access_token!!
    }


    @Test
    fun repositories() {

    }
}