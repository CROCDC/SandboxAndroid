package com.cr.o.cdc.sandboxAndroid.whatsapputils.db.dao

import com.cr.o.cdc.sandboxAndroid.coronavirus.di.CoronavirusModule
import com.cr.o.cdc.sandboxAndroid.utils.DBTest
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.model.WhatsappMessageAutoReply
import com.cr.o.cdc.sharedtest.getValueLiveData
import com.cr.o.cdc.sharedtest.makeRandomInstance
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertTrue
import org.junit.Test

@HiltAndroidTest
@UninstallModules(CoronavirusModule::class)
class WhatsappMessageAutoReplyDaoTest : DBTest() {

    private val dao = db.whatsappAutoReplyDao()

    private val whatsappMessageAutoReply: WhatsappMessageAutoReply =
        WhatsappMessageAutoReply::class.makeRandomInstance()

    @Test
    fun save() {
        dao.save(whatsappMessageAutoReply)
    }

    @Test
    fun loadAll() {
        dao.save(whatsappMessageAutoReply)

        assertTrue(getValueLiveData(dao.loadAll(), 2)?.isNotEmpty() == true)
    }
}
