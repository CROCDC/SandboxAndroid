package com.cr.o.cdc.sandboxAndroid.whatsapputils.vm

import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.whatsapputils.repos.WhatsappMessagesRepository
import javax.inject.Inject

class WhatsappMesaggesViewModel @Inject constructor(repository: WhatsappMessagesRepository) :
    ViewModel() {


    val whatsappMessages = repository.loadWhatsappMessagges()
}