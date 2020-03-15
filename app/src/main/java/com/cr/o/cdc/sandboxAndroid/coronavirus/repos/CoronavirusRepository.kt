package com.cr.o.cdc.sandboxAndroid.coronavirus.repos

import androidx.lifecycle.LiveData
import com.cr.o.cdc.networking.RetrofitResponse
import com.cr.o.cdc.sandboxAndroid.coronavirus.model.CasesByCountry
import javax.inject.Inject

class CoronavirusRepository @Inject constructor(private val service: CoronavirusService) {

    fun getCasesByCountry(): LiveData<RetrofitResponse<CasesByCountry>> =
        service.getCasesByCountry()

}