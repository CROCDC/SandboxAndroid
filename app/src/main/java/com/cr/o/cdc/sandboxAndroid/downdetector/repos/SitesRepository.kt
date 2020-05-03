package com.cr.o.cdc.sandboxAndroid.downdetector.repos

import androidx.lifecycle.LiveData
import com.cr.o.cdc.sandboxAndroid.downdetector.db.model.Site
import com.cr.o.cdc.sandboxAndroid.downdetector.model.PingResponse
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.SandBoxDB
import javax.inject.Inject

class SitesRepository @Inject constructor(
    val db: SandBoxDB,
    private val dataSource: SitesDataSourceProvider
) {

    fun getAllSites(): LiveData<List<Site>> = db.siteDao().getAll()

    fun saveSite(site: Site): Unit = db.siteDao().save(site)

    fun modifyEnable(address: String, enable: Boolean, workRequestId: String?) {
        db.siteDao().modifyEnable(address, enable, workRequestId)
    }

    fun pingSite(address: String): PingResponse {
        val response = dataSource.ping(address)

        when (response) {
            is PingResponse.PingSuccess -> {
                db.siteDao().siteIsWorking(address)
            }
            else -> {
                val site = db.siteDao().find(address)
                if (site.isWorking == true || site.isWorking == null) {
                    db.siteDao().siteNotWorking(site.cantErrors.inc(), site.address)
                } else {
                    db.siteDao().setNumberOfRetriesOfError(
                        site.address,
                        (site.numberOfRetriesOfError ?: 0).inc()
                    )
                }
            }
        }

        return response
    }
}
