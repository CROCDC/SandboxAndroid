package com.cr.o.cdc.sandboxAndroid.coronavirus.repos

import androidx.lifecycle.LiveData
import com.cr.o.cdc.sandboxAndroid.coronavirus.model.CasesByCountry
import com.cr.o.cdc.sandboxAndroid.libraries.networking.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface CoronavirusService {

    @Headers(
        "x-rapidapi-key: 97a6051f55mshcf6b729c2663e41p1a6fc6jsn5b37a3c4e33d",
        "x-rapidapi-host: coronavirus-monitor.p.rapidapi.com"
    )
    @GET("cases_by_country.php")
    fun getCasesByCountry(): LiveData<NetworkResponse<CasesByCountry>>
}
