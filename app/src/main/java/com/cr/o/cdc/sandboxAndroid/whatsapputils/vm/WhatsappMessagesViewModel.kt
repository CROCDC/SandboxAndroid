package com.cr.o.cdc.sandboxAndroid.whatsapputils.vm

import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.whatsapputils.repos.WhatsappMessagesRepository
import javax.inject.Inject

class WhatsappMessagesViewModel @Inject constructor(repository: WhatsappMessagesRepository) :
    ViewModel() {

    val whatsappMessages = repository.loadWhatsappMessages()
}
