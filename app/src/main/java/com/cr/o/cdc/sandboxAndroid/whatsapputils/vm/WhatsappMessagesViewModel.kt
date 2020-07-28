package com.cr.o.cdc.sandboxAndroid.whatsapputils.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.model.WhatsappMessage
import com.cr.o.cdc.sandboxAndroid.whatsapputils.repos.WhatsappMessagesRepository

class WhatsappMessagesViewModel @ViewModelInject constructor(repo: WhatsappMessagesRepository) :
    ViewModel() {

    val whatsappMessages: LiveData<List<WhatsappMessage>> = repo.loadWhatsappMessages()
}
