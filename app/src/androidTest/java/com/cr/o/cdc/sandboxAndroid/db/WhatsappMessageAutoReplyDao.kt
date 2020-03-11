package com.cr.o.cdc.sandboxAndroid.db

import com.cr.o.cdc.sandboxAndroid.utils.DBTest
import com.cr.o.cdc.sandboxAndroid.whatsapputils.model.WhatsappMessageAutoReply
import com.cr.o.cdc.sharedtest.getValueLiveData
import com.cr.o.cdc.sharedtest.makeRandomInstance
import junit.framework.TestCase.assertTrue
import org.junit.Test

class WhatsappMessageAutoReplyDao : DBTest() {

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