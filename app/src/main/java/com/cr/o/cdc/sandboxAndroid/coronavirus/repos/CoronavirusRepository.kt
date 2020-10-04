package com.cr.o.cdc.sandboxAndroid.coronavirus.repos

import androidx.lifecycle.LiveData
import com.cr.o.cdc.networking.AppExecutors
import com.cr.o.cdc.networking.NetworkBoundResource
import com.cr.o.cdc.networking.NetworkResource
import com.cr.o.cdc.networking.NetworkResponse
import com.cr.o.cdc.sandboxAndroid.coronavirus.db.model.CountryStat
import com.cr.o.cdc.sandboxAndroid.coronavirus.model.CasesByCountry
import com.cr.o.cdc.sandboxAndroid.SandBoxDB
import javax.inject.Inject

class CoronavirusRepository @Inject constructor(
    private val service: CoronavirusService,
    private val db: SandBoxDB,
    private val appExecutors: AppExecutors
) {

    fun getCasesByCountry(): LiveData<NetworkResource<List<CountryStat>>> =
        object : NetworkBoundResource<List<CountryStat>, CasesByCountry>(appExecutors) {
            override fun saveCallResult(item: CasesByCountry?) {
                db.countryStatDao().deleteAll()
                item?.countries_stat?.let { db.countryStatDao().saveAll(it) }
            }

            override fun shouldFetch(data: List<CountryStat>?): Boolean = true

            override fun loadFromDb(): LiveData<List<CountryStat>> = db.countryStatDao().loadAll()

            override fun createCall(): LiveData<NetworkResponse<CasesByCountry>> =
                service.getCasesByCountry()
        }.asLiveData()

    fun search(country: String): LiveData<List<CountryStat>> = db.countryStatDao().search(country)
}
