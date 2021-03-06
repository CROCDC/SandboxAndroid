package com.cr.o.cdc.sandboxAndroid.downdetector.db.dao

import com.cr.o.cdc.sandboxAndroid.coronavirus.di.CoronavirusModule
import com.cr.o.cdc.sandboxAndroid.downdetector.fake.MockFactoryDownDetector
import com.cr.o.cdc.sandboxAndroid.utils.DBTest
import com.cr.o.cdc.sharedtest.getValueLiveData
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

@HiltAndroidTest
@UninstallModules(CoronavirusModule::class)
class SiteDaoTest : DBTest() {

    private val dao = db.siteDao()

    private val site = MockFactoryDownDetector.getSite()

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
        dao.siteNotWorking(1, site.address, "error")

        assertEquals(
            dao.find(site.address),
            site.copy(cantErrors = 1, isWorking = false, lastErrorMessage = "error")
        )
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

    @Test
    fun delete() {
        dao.delete(site.address)

        assertEquals(dao.find(site.address), null)
    }
}
