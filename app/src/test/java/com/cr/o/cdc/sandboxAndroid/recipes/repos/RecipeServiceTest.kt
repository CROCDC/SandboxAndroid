package com.cr.o.cdc.sandboxAndroid.recipes.repos

import com.cr.o.cdc.sandboxAndroid.pagination.repos.RecipeService
import com.cr.o.cdc.sandboxAndroid.utils.EndpointTest
import com.cr.o.cdc.networking.LiveDataCallAdapterFactory
import junit.framework.TestCase.assertTrue
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeServiceTest : EndpointTest() {

    private val recipeService: RecipeService = Retrofit.Builder()
        .baseUrl("https://api.edamam.com/search/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .build().create(RecipeService::class.java)


    @Test
    fun search() {
        val response =
            recipeService.search(
                "fish",
                10,
                0,
                "41ce6696",
                "416e06f4fa1b46dacd17ca14a1d563c9"
            ).execute()

        assertTrue(
            response.body() != null
        )
        print(response.body())
    }

}