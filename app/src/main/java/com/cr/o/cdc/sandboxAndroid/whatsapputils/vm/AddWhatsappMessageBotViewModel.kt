package com.cr.o.cdc.sandboxAndroid.whatsapputils.vm

import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.model.WhatsappMessageBot
import com.cr.o.cdc.sandboxAndroid.whatsapputils.repos.WhatsappMessagesRepository
import javax.inject.Inject

class AddWhatsappMessageBotViewModel @Inject constructor(
    val repository: WhatsappMessagesRepository
) : ViewModel() {

    fun add(whatsappMessageBot: WhatsappMessageBot) {
        repository.saveWhatsappMessageBot(whatsappMessageBot)
    }
}
