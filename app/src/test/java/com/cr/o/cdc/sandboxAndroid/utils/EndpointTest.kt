package com.cr.o.cdc.sandboxAndroid.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cr.o.cdc.networking.SuccessResponse
import com.cr.o.cdc.sandboxAndroid.bitbucket.di.AppModuleBitbucket
import com.cr.o.cdc.sandboxAndroid.bitbucket.model.LoginResponse
import com.cr.o.cdc.sharedtest.getValue
import okhttp3.MultipartBody
import org.junit.Rule
import retrofit2.Retrofit
import java.util.Base64

abstract class EndpointTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    fun getAccessTokenBitbucket() = (getValue(
        AppModuleBitbucket().provideBitbucketAuthService(Retrofit.Builder()).login(
            "Basic ${Base64.getEncoder()
                .encodeToString("HsRFzLz7NGbaYg2YRs:AccxNmY67CApPPYQ3zV7SF46vU6bYN6J".toByteArray())}",
            MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("username", "CajaDeArenaTests")
                .addFormDataPart("password", "Monroe4500")
                .addFormDataPart("grant_type", "password")
                .build()
        )
    ) as SuccessResponse<LoginResponse>).data?.access_token!!
}
