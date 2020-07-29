package com.cr.o.cdc.sandboxAndroid.whatsapputils.fake

import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.model.WhatsappMessage
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.model.WhatsappMessageAutoReply
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.model.WhatsappMessageBot

object MockFactoryWhatsappUtils {

    fun getWhatsappMessageAutoReply(): WhatsappMessageAutoReply = WhatsappMessageAutoReply(
        "contact",
        true
    )

    fun getWhatsappMessageBot(): WhatsappMessageBot = WhatsappMessageBot(
        "contact",
        "response",
        "message"
    )

    fun getWhatsappMessage() = WhatsappMessage("message", null)
}
