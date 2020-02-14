package com.cr.o.cdc.sandboxAndroid.pagination.repos

import com.cr.o.cdc.sandboxAndroid.pagination.model.RecipeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {

    @GET("/search")
    fun search(
        @Query(value = "q") search: String,
        @Query(value = "from") to: Int,
        @Query(value = "from") from: Int,
        @Query(value = "app_id") appId: String,
        @Query(value = "app_key") apiKey: String
    ): Call<RecipeResponse>
}
