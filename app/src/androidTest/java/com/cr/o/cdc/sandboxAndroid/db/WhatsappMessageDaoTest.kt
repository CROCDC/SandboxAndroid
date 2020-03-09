package com.cr.o.cdc.sandboxAndroid.db

import com.cr.o.cdc.sandboxAndroid.utils.DBTest
import com.cr.o.cdc.sandboxAndroid.whatsapputils.model.WhatsappMessage
import com.cr.o.cdc.sharedtest.getValueLivedata
import com.cr.o.cdc.sharedtest.makeRandomInstance
import junit.framework.TestCase.assertTrue
import org.junit.Test

class WhatsappMessageDaoTest : DBTest() {

    private val dao = db.whatsappMessageDao()

    val whatsappMessage: WhatsappMessage = WhatsappMessage::class.makeRandomInstance()

    @Test
    fun save() {
        dao.save(whatsappMessage)
    }

    @Test
    fun loadAll() {
        dao.save(whatsappMessage)

        assertTrue(getValueLivedata(dao.loadAll(), 2)?.isNotEmpty() == true)
    }

}