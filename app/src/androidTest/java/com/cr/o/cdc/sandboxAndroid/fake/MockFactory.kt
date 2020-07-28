package com.cr.o.cdc.sandboxAndroid.fake

import androidx.lifecycle.MutableLiveData
import com.cr.o.cdc.networking.NetworkResponse
import com.cr.o.cdc.sandboxAndroid.coronavirus.db.model.CountryStat
import com.cr.o.cdc.sandboxAndroid.coronavirus.model.CasesByCountry
import retrofit2.Response

object MockFactory {

    fun getCasesByCountry(): CasesByCountry = CasesByCountry(listOf(getCountryStat()))

    fun getCountryStat(): CountryStat = CountryStat("Inviable", "100", "50")


}

fun <T : Any> T.mutableLiveDataInNetworkResponse() =
    MutableLiveData(NetworkResponse.create(Response.success(this)))

