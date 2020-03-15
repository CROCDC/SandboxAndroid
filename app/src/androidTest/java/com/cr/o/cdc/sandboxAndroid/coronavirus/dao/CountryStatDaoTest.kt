package com.cr.o.cdc.sandboxAndroid.coronavirus.dao

import com.cr.o.cdc.sandboxAndroid.coronavirus.model.CountryStat
import com.cr.o.cdc.sandboxAndroid.utils.DBTest
import com.cr.o.cdc.sharedtest.getValueLiveData
import com.cr.o.cdc.sharedtest.makeRandomInstance
import junit.framework.TestCase.assertTrue
import org.junit.Test

class CountryStatDaoTest : DBTest() {
    private val countryStatDao = db.countryStatDao()


    private val list = listOf<CountryStat>(
        CountryStat::class.makeRandomInstance(), CountryStat::class.makeRandomInstance()
    )

    @Test
    fun deleteAll() {
        countryStatDao.saveAll(list)

        countryStatDao.deleteAll()

        assertTrue(getValueLiveData(countryStatDao.loadAll(), 2)?.isEmpty() == true)
    }

}