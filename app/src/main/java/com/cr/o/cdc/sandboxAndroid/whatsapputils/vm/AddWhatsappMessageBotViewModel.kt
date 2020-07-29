package com.cr.o.cdc.sandboxAndroid.whatsapputils.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.model.WhatsappMessageBot
import com.cr.o.cdc.sandboxAndroid.whatsapputils.repos.WhatsappMessagesRepository

class AddWhatsappMessageBotViewModel @ViewModelInject constructor(
    private val repository: WhatsappMessagesRepository
) : ViewModel() {

    fun add(whatsappMessageBot: WhatsappMessageBot) {
        repository.saveWhatsappMessageBot(whatsappMessageBot)
    }
}
