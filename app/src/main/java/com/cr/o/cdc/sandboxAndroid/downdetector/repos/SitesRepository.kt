package com.cr.o.cdc.sandboxAndroid.downdetector.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cr.o.cdc.sandboxAndroid.downdetector.db.model.Site
import com.cr.o.cdc.sandboxAndroid.downdetector.model.PingResponse
import com.cr.o.cdc.sandboxAndroid.libraries.networking.AppExecutors
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.SandBoxDB
import javax.inject.Inject

class SitesRepository @Inject constructor(
    private val db: SandBoxDB,
    private val dataSource: SitesDataSourceProvider,
    private val appExecutors: AppExecutors
) {

    fun getAllSites(): LiveData<List<Site>> = db.siteDao().getAll()

    fun saveSite(site: Site): Unit = db.siteDao().save(site)

    fun modifyEnable(address: String, enable: Boolean, workRequestId: String?) {
        db.siteDao().modifyEnable(address, enable, workRequestId)
    }

    fun pingSite(address: String): PingResponse {
        val response = dataSource.ping(address)

        if (response is PingResponse.PingSuccess) {
            db.siteDao().siteIsWorking(address)
        } else {
            val site = db.siteDao().find(address)

            val errorMessage = when (response) {
                is PingResponse.PingError -> response.response.toString()
                is PingResponse.PingException -> response.message
                else -> null
            }
            if (site.isWorking == true || site.isWorking == null) {
                db.siteDao()
                    .siteNotWorking(site.cantErrors.inc(), site.address, errorMessage)
            } else {
                db.siteDao().setNumberOfRetriesOfError(
                    site.address,
                    (site.numberOfRetriesOfError ?: 0).inc()
                )
            }
        }

        return response
    }

    fun asyncPing(address: String): MutableLiveData<PingResponse> {
        val mutableLiveData = MutableLiveData<PingResponse>()

        appExecutors.networkIO().execute {
            mutableLiveData.postValue(pingSite(address))
        }

        return mutableLiveData
    }

    fun deleteSite(address: String) {
        db.siteDao().delete(address)
    }
}
