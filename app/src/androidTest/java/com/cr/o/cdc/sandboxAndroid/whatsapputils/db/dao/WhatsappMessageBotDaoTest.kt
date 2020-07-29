package com.cr.o.cdc.sandboxAndroid.whatsapputils.db.dao

import com.cr.o.cdc.sandboxAndroid.coronavirus.di.CoronavirusModule
import com.cr.o.cdc.sandboxAndroid.utils.DBTest
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.model.WhatsappMessageBot
import com.cr.o.cdc.sandboxAndroid.whatsapputils.fake.MockFactoryWhatsappUtils
import com.cr.o.cdc.sharedtest.getValueLiveData
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertTrue
import org.junit.Test

@HiltAndroidTest
@UninstallModules(CoronavirusModule::class)
class WhatsappMessageBotDaoTest : DBTest() {

    private val dao = db.whatsappMessageBotDao()

    private val whatsappMessageBot: WhatsappMessageBot =
        MockFactoryWhatsappUtils.getWhatsappMessageBot()

    @Test
    fun find() {
        dao.save(whatsappMessageBot)

        assertTrue(dao.find(whatsappMessageBot.contact, whatsappMessageBot.message).isNotEmpty())
    }

    @Test
    fun save() {
        dao.save(whatsappMessageBot)
    }

    @Test
    fun loadAll() {
        dao.save(whatsappMessageBot)

        assertTrue(getValueLiveData(dao.loadAll(), 2)?.isNotEmpty() == true)
    }
}
