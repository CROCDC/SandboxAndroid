package com.cr.o.cdc.sandboxAndroid.coronavirus.repos

import com.cr.o.cdc.networking.LiveDataCallAdapterFactory
import com.cr.o.cdc.networking.SuccessResponse
import com.cr.o.cdc.sandboxAndroid.utils.EndpointTest
import com.cr.o.cdc.sharedtest.getValue
import junit.framework.TestCase.assertTrue
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoronavirusServiceTest : EndpointTest() {

    private val service: CoronavirusService = Retrofit.Builder()
        .baseUrl("https://coronavirus-monitor.p.rapidapi.com/coronavirus/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .build().create(CoronavirusService::class.java)

    @Test
    fun getCasesByCountry() {
        val response = getValue(service.getCasesByCountry())
        assertTrue((response as? SuccessResponse)?.data != null)
    }
}