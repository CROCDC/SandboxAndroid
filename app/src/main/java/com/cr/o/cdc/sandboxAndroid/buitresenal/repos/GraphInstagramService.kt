package com.cr.o.cdc.sandboxAndroid.buitresenal.repos

import androidx.lifecycle.LiveData
import com.cr.o.cdc.sandboxAndroid.buitresenal.db.model.InstagramUser
import com.cr.o.cdc.sandboxAndroid.libraries.networking.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Cami on 5/22/21.
 */
interface GraphInstagramService {

    @GET("/{user_id}")
    fun getUser(
        @Path("user_id") userId: String,
        @Query("fields") fields: String,
        @Query("access_token") accessToken: String
    ): LiveData<NetworkResponse<InstagramUser>>
}