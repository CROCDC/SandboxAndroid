package com.cr.o.cdc.sandboxAndroid.coronavirus.fake

import androidx.lifecycle.LiveData
import com.cr.o.cdc.networking.NetworkResponse
import com.cr.o.cdc.sandboxAndroid.coronavirus.model.CasesByCountry
import com.cr.o.cdc.sandboxAndroid.coronavirus.repos.CoronavirusService

class FakeCoronavirusService : CoronavirusService {
    override fun getCasesByCountry(): LiveData<NetworkResponse<CasesByCountry>> {
        return MockFactoryCoronavirus.getCasesByCountry().mutableLiveDataInNetworkResponse()
    }
}
