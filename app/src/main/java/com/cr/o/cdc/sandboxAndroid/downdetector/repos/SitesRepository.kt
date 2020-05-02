package com.cr.o.cdc.sandboxAndroid.downdetector.repos

import androidx.lifecycle.LiveData
import com.cr.o.cdc.sandboxAndroid.db.SandBoxDB
import com.cr.o.cdc.sandboxAndroid.downdetector.db.model.Site
import javax.inject.Inject

class SitesRepository @Inject constructor(val db: SandBoxDB) {

    fun getAllSites(): LiveData<List<Site>> = db.siteDao().getAll()

    fun saveSite(site: Site) = db.siteDao().save(site)

    fun modifyEnable(address: String, enable: Boolean) {
        db.siteDao().modifyEnable(address, enable)
    }
}