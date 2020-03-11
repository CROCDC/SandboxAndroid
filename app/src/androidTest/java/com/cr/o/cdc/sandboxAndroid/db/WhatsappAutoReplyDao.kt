package com.cr.o.cdc.sandboxAndroid.db

import com.cr.o.cdc.sandboxAndroid.utils.DBTest
import com.cr.o.cdc.sandboxAndroid.whatsapputils.model.WhatsappAutoReply
import com.cr.o.cdc.sharedtest.getValueLiveData
import com.cr.o.cdc.sharedtest.makeRandomInstance
import junit.framework.TestCase.assertTrue
import org.junit.Test

class WhatsappAutoReplyDao : DBTest() {

    private val dao = db.whatsappAutoReplyDao()

    private val whatsappAutoReply: WhatsappAutoReply =
        WhatsappAutoReply::class.makeRandomInstance()


    @Test
    fun save() {
        dao.save(whatsappAutoReply)
    }

    @Test
    fun loadAll() {
        dao.save(whatsappAutoReply)

        assertTrue(getValueLiveData(dao.loadAll(), 2)?.isNotEmpty() == true)
    }
}