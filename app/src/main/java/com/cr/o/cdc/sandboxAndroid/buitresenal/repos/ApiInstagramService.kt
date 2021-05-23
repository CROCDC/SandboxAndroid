package com.cr.o.cdc.sandboxAndroid.buitresenal.repos

import androidx.lifecycle.LiveData
import com.cr.o.cdc.sandboxAndroid.buitresenal.model.AccessTokenResponse
import com.cr.o.cdc.sandboxAndroid.libraries.networking.NetworkResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Part

/**
 * Created by Cami on 5/22/21.
 */
interface ApiInstagramService {

    @FormUrlEncoded
    @POST("/oauth/access_token")
    fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") appSecret: String,
        @Field("grant_type") grantType: String,
        @Field("redirect_uri") redirectUri: String,
        @Field("code") code: String
    ): LiveData<NetworkResponse<AccessTokenResponse>>
}
