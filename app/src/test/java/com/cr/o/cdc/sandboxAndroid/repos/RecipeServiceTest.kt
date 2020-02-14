package com.cr.o.cdc.sandboxAndroid.repos

import android.content.res.Resources
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.SandBoxApp
import com.cr.o.cdc.sandboxAndroid.di.AppModule
import com.cr.o.cdc.sandboxAndroid.pagination.repos.RecipeService
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import org.junit.Test

class RecipeServiceTest : EndpointTest() {

    val recipeService = AppModule().provideRetrofit(mockk<SandBoxApp>().apply {
        every { resources } returns mockk<Resources>().apply {
            every { getString(R.string.recipes_url) } returns "https://api.edamam.com/search/"
        }
    }).create(RecipeService::class.java)


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