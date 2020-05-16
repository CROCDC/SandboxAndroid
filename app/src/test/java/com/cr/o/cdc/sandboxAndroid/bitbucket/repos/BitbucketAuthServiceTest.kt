package com.cr.o.cdc.sandboxAndroid.bitbucket.repos


import com.cr.o.cdc.networking.SuccessResponse
import com.cr.o.cdc.sandboxAndroid.bitbucket.di.AppModuleBitbucket
import com.cr.o.cdc.sandboxAndroid.utils.EndpointTest
import com.cr.o.cdc.sharedtest.getValue
import junit.framework.TestCase.assertTrue
import okhttp3.MultipartBody
import org.junit.Test
import retrofit2.Retrofit
import java.util.Base64

class BitbucketAuthServiceTest : EndpointTest() {

    private val service: BitbucketAuthService =
        AppModuleBitbucket().provideBitbucketAuthService(Retrofit.Builder())


    @Test
    fun login() {
        val keyAndSecret = "HsRFzLz7NGbaYg2YRs:AccxNmY67CApPPYQ3zV7SF46vU6bYN6J"

        val response = getValue(
            service.login(
                "Basic ${Base64.getEncoder().encodeToString(keyAndSecret.toByteArray())}",
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