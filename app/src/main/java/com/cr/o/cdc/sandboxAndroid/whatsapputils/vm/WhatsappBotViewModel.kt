package com.cr.o.cdc.sandboxAndroid.whatsapputils.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.cr.o.cdc.sandboxAndroid.whatsapputils.model.WhatsappMessageBot
import com.cr.o.cdc.sandboxAndroid.whatsapputils.repos.WhatsappMessagesRepository
import com.cr.o.cdc.sandboxAndroid.whatsapputils.vo.WhatsappConfig
import javax.inject.Inject

class WhatsappBotViewModel @Inject constructor(
    private val whatsappConfig: WhatsappConfig,
    repository: WhatsappMessagesRepository
) :
    ViewModel() {

    val isEnabled: LiveData<Boolean> = whatsappConfig.map {
        it.enable
    }

    val whatsappBotMessages: LiveData<List<WhatsappMessageBot>> =
        repository.findAllWhatsappMessagesBot()

    fun modify() {
        whatsappConfig.updateWhatsappConfigInfo(!(isEnabled.value ?: false))
    }
}