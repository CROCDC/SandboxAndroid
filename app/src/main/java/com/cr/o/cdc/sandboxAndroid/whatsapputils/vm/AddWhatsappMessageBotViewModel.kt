package com.cr.o.cdc.sandboxAndroid.whatsapputils.vm

import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.model.WhatsappMessageBot
import com.cr.o.cdc.sandboxAndroid.whatsapputils.repos.WhatsappMessagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddWhatsappMessageBotViewModel @Inject constructor(
    private val repository: WhatsappMessagesRepository
) : ViewModel() {

    fun add(whatsappMessageBot: WhatsappMessageBot) {
        repository.saveWhatsappMessageBot(whatsappMessageBot)
    }
}
