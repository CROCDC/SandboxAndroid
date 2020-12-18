package com.cr.o.cdc.sandboxAndroid.coronavirus.fake

import androidx.lifecycle.MutableLiveData
import com.cr.o.cdc.sandboxAndroid.libraries.networking.NetworkResponse
import com.cr.o.cdc.sandboxAndroid.coronavirus.db.model.CountryStat
import com.cr.o.cdc.sandboxAndroid.coronavirus.model.CasesByCountry
import retrofit2.Response

object MockFactoryCoronavirus {

    fun getListOfCountryStat(): List<CountryStat> = listOf(getCountryStat())

    fun getCasesByCountry(): CasesByCountry = CasesByCountry(listOf(getCountryStat()))

    private fun getCountryStat(): CountryStat = CountryStat("Inviable", "100", "50")
}

fun <T : Any> T.mutableLiveDataInNetworkResponse() =
    MutableLiveData(NetworkResponse.create(Response.success(this)))
