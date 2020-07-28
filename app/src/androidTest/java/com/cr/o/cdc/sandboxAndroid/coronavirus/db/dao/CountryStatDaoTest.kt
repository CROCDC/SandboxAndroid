package com.cr.o.cdc.sandboxAndroid.coronavirus.db.dao

import com.cr.o.cdc.sandboxAndroid.coronavirus.db.model.CountryStat
import com.cr.o.cdc.sandboxAndroid.coronavirus.di.CoronavirusModule
import com.cr.o.cdc.sandboxAndroid.utils.DBTest
import com.cr.o.cdc.sharedtest.getValueLiveData
import com.cr.o.cdc.sharedtest.makeRandomInstance
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertTrue
import org.junit.Test

@HiltAndroidTest
@UninstallModules(CoronavirusModule::class)
class CountryStatDaoTest : DBTest() {
    private val countryStatDao = db.countryStatDao()

    private val list = listOf<CountryStat>(
        CountryStat::class.makeRandomInstance(), CountryStat::class.makeRandomInstance()
    )

    @Test
    fun deleteAll() {
        countryStatDao.saveAll(list)

        countryStatDao.deleteAll()

        Thread.sleep(200)

        assertTrue(getValueLiveData(countryStatDao.loadAll(), 2)?.isNullOrEmpty() == true)
    }
}
