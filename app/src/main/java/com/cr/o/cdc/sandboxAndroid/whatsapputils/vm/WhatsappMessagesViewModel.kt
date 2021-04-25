package com.cr.o.cdc.sandboxAndroid.whatsapputils.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.model.WhatsappMessage
import com.cr.o.cdc.sandboxAndroid.whatsapputils.repos.WhatsappMessagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WhatsappMessagesViewModel @Inject constructor(repo: WhatsappMessagesRepository) :
    ViewModel() {

    val whatsappMessages: LiveData<List<WhatsappMessage>> = repo.loadWhatsappMessages()
}
