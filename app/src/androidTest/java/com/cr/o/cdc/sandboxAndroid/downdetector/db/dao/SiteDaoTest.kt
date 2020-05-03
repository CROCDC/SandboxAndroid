package com.cr.o.cdc.sandboxAndroid.downdetector.db.dao

import com.cr.o.cdc.sandboxAndroid.downdetector.db.model.Site
import com.cr.o.cdc.sandboxAndroid.utils.DBTest
import com.cr.o.cdc.sharedtest.getValueLiveData
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class SiteDaoTest : DBTest() {

    private val dao = db.siteDao()

    private val site = Site("address", "name", true, 20, 0, null, null, null)

    @Before
    fun saveSite() {
        dao.save(site)
    }

    @Test
    fun loadAll() {
        assertEquals(getValueLiveData(dao.getAll(), 5)?.get(0), site)
    }

    @Test
    fun modifyEnable() {
        dao.modifyEnable(site.address, false, "work")

        assertEquals(
            getValueLiveData(dao.getAll(), 5)?.get(0),
            site.copy(enable = false, workRequestId = "work")
        )
    }

    @Test
    fun setErrorOfSite() {
        dao.siteNotWorking(1, site.address)

        assertEquals(dao.find(site.address), site.copy(cantErrors = 1, isWorking = false))
    }

    @Test
    fun siteIsWorking() {
        dao.siteIsWorking(site.address)

        assertEquals(dao.find(site.address), site.copy(isWorking = true))
    }

    @Test
    fun setNumberOfRetriesOfError() {
        dao.setNumberOfRetriesOfError(site.address, 1)

        assertEquals(dao.find(site.address), site.copy(numberOfRetriesOfError = 1))
    }
}
