package com.cr.o.cdc.sandboxAndroid.db

import com.cr.o.cdc.sandboxAndroid.utils.DBTest
import com.cr.o.cdc.sandboxAndroid.whatsapputils.model.WhatsappMessageBot
import com.cr.o.cdc.sharedtest.makeRandomInstance
import junit.framework.TestCase.assertTrue
import org.junit.Test

class WhatsappMessageBotDaoTest : DBTest() {

    private val dao = db.whatsappMessageBotDao()

    private val whatsappMessageBot: WhatsappMessageBot =
        WhatsappMessageBot::class.makeRandomInstance()

    @Test
    fun find() {
        dao.save(whatsappMessageBot)

        assertTrue(dao.find(whatsappMessageBot.contact, whatsappMessageBot.message).isNotEmpty())

    }
}