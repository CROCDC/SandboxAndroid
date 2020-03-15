package com.cr.o.cdc.sandboxAndroid.coronavirus.repos

import androidx.lifecycle.LiveData
import com.cr.o.cdc.networking.AppExecutors
import com.cr.o.cdc.networking.NetworkBoundResource
import com.cr.o.cdc.networking.RetrofitResource
import com.cr.o.cdc.networking.RetrofitResponse
import com.cr.o.cdc.sandboxAndroid.coronavirus.model.CasesByCountry
import com.cr.o.cdc.sandboxAndroid.coronavirus.model.CountryStat
import com.cr.o.cdc.sandboxAndroid.db.SandBoxDB
import javax.inject.Inject

class CoronavirusRepository @Inject constructor(
    private val service: CoronavirusService,
    private val db: SandBoxDB,
    private val appExecutors: AppExecutors
) {

    fun getCasesByCountry(): LiveData<RetrofitResource<List<CountryStat>>> =
        object : NetworkBoundResource<List<CountryStat>, CasesByCountry>(appExecutors) {
            override fun saveCallResult(item: CasesByCountry?) {
                item?.countries_stat?.let { db.countryStatDao().saveAll(it) }
            }

            override fun shouldFetch(data: List<CountryStat>?): Boolean = true

            override fun loadFromDb(): LiveData<List<CountryStat>> = db.countryStatDao().loadAll()

            override fun createCall(): LiveData<RetrofitResponse<CasesByCountry>> =
                service.getCasesByCountry()

        }.asLiveData()
}