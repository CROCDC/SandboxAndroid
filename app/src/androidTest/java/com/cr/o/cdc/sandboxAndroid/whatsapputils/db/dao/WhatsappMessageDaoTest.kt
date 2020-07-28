package com.cr.o.cdc.sandboxAndroid.whatsapputils.db.dao

import com.cr.o.cdc.sandboxAndroid.coronavirus.di.CoronavirusModule
import com.cr.o.cdc.sandboxAndroid.utils.DBTest
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.model.WhatsappMessage
import com.cr.o.cdc.sharedtest.getValueLiveData
import com.cr.o.cdc.sharedtest.makeRandomInstance
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertTrue
import org.junit.Test

@HiltAndroidTest
@UninstallModules(CoronavirusModule::class)
class WhatsappMessageDaoTest : DBTest() {

    private val dao = db.whatsappMessageDao()

    private val whatsappMessage: WhatsappMessage = WhatsappMessage::class.makeRandomInstance()

    @Test
    fun save() {
        dao.save(whatsappMessage)
    }

    @Test
    fun loadAll() {
        dao.save(whatsappMessage)

        assertTrue(getValueLiveData(dao.loadAll(), 2)?.isNotEmpty() == true)
    }
}
