package com.cr.o.cdc.sandboxAndroid.bitbucket.repos

import androidx.lifecycle.LiveData
import com.cr.o.cdc.networking.NetworkResponse
import com.cr.o.cdc.sandboxAndroid.bitbucket.model.LoginResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface BitbucketAuthService {

    @POST("access_token")
    fun login(
        @Header("Authorization") authHeader: String,
        @Body requestBody: RequestBody
    ): LiveData<NetworkResponse<LoginResponse>>

}
