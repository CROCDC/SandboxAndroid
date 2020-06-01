package com.cr.o.cdc.sandboxAndroid.bitbucket.repos

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.cr.o.cdc.networking.AppExecutors
import com.cr.o.cdc.networking.NetworkResource
import com.cr.o.cdc.networking.NetworkResponse
import com.cr.o.cdc.networking.SimpleNetworkResource
import com.cr.o.cdc.sandboxAndroid.bitbucket.model.LoginResponse
import okhttp3.MultipartBody
import java.util.Base64
import javax.inject.Inject

class BitbucketAuthRepository @Inject constructor(
    val service: BitbucketAuthService,
    val sharedPreferences: SharedPreferences,
    val appExecutors: AppExecutors
) {

    fun login(
        key: String,
        secret: String,
        username: String,
        password: String
    ): LiveData<NetworkResource<LoginResponse>> =
        object : SimpleNetworkResource<LoginResponse>(appExecutors) {
            override fun saveData(data: LoginResponse) {
                data.let {
                    sharedPreferences.edit()
                        .putString(BitbucketAuthService.ACCESS_TOKEN, it.access_token)
                        .putString(BitbucketAuthService.REFRESH_TOKEN, it.refresh_token)
                        .commit()
                }
            }

            //TODO api 26
            override fun createCall(): LiveData<NetworkResponse<LoginResponse>> = service.login(
                "Basic ${Base64.getEncoder().encodeToString("$key:$secret".toByteArray())}",
                MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("username", username)
                    .addFormDataPart("password", password)
                    .addFormDataPart("grant_type", "password")
                    .build()
            )

        }.asLiveData()
}