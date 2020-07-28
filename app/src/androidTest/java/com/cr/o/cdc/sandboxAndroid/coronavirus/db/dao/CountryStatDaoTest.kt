package com.cr.o.cdc.sandboxAndroid.coronavirus.db.dao

import com.cr.o.cdc.sandboxAndroid.coronavirus.di.CoronavirusModule
import com.cr.o.cdc.sandboxAndroid.coronavirus.fake.MockFactoryCoronavirus
import com.cr.o.cdc.sandboxAndroid.utils.DBTest
import com.cr.o.cdc.sharedtest.getValueLiveData
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertTrue
import org.junit.Test

@HiltAndroidTest
@UninstallModules(CoronavirusModule::class)
class CountryStatDaoTest : DBTest() {

    private val countryStatDao = db.countryStatDao()

    @Test
    fun deleteAll() {
        countryStatDao.saveAll(MockFactoryCoronavirus.getListOfCountryStat())

        countryStatDao.deleteAll()

        Thread.sleep(200)

        assertTrue(getValueLiveData(countryStatDao.loadAll(), 2)?.isNullOrEmpty() == true)
    }
}
