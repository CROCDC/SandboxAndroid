package com.cr.o.cdc.sandboxAndroid.fake

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.cr.o.cdc.networking.NetworkResponse
import com.cr.o.cdc.sandboxAndroid.coronavirus.model.CasesByCountry
import com.cr.o.cdc.sandboxAndroid.coronavirus.repos.CoronavirusService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FakeCoronavirusService @Inject constructor(@ApplicationContext private val context: Context) :
    CoronavirusService {
    override fun getCasesByCountry(): LiveData<NetworkResponse<CasesByCountry>> {
        Log.e("c", context.packageName)
        return MockFactory.getCasesByCountry().mutableLiveDatainNetworkResponse()
    }

}