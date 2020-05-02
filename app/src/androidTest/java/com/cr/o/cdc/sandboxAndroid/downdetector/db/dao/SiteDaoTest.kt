package com.cr.o.cdc.sandboxAndroid.downdetector.db.dao

import com.cr.o.cdc.sandboxAndroid.downdetector.db.model.Site
import com.cr.o.cdc.sandboxAndroid.utils.DBTest
import com.cr.o.cdc.sharedtest.getValueLiveData
import junit.framework.TestCase.assertEquals
import org.junit.Test

class SiteDaoTest : DBTest() {

    private val dao = db.siteDao()

    private val site = Site("address", "name", true, 20)

    @Test
    fun save() {
        dao.save(site)
    }

    @Test
    fun loadAll() {
        dao.save(site)

        assertEquals(getValueLiveData(dao.getAll(), 5)?.get(0), site)
    }

    @Test
    fun modifyEnable() {
        dao.save(site)

        dao.modifyEnable(site.address, false)

        assertEquals(getValueLiveData(dao.getAll(), 5)?.get(0), site.copy(enable = false))

    }

}