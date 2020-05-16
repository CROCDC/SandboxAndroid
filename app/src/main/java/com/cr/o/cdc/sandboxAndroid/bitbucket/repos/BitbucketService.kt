package com.cr.o.cdc.sandboxAndroid.bitbucket.repos

import androidx.lifecycle.LiveData
import com.cr.o.cdc.networking.NetworkResponse
import retrofit2.http.Header

interface BitbucketService {

    fun repositories(
        @Header("Authorization") accessToken: String
    ): LiveData<NetworkResponse<Boolean>>
}
