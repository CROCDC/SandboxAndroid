package com.cr.o.cdc.sandboxAndroid.whatsapputils.repos

import androidx.lifecycle.LiveData
import com.cr.o.cdc.networking.AppExecutors
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.SandBoxDB
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.model.WhatsappMessage
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.model.WhatsappMessageBot
import javax.inject.Inject

class WhatsappMessagesRepository @Inject constructor(
    val db: SandBoxDB,
    private val appExecutors: AppExecutors
) {

    fun saveWhatsappMessage(whatsappMessage: WhatsappMessage) {
        appExecutors.diskIO().execute {
            db.whatsappMessageDao().save(whatsappMessage)
        }
    }

    fun loadWhatsappMessages(): LiveData<List<WhatsappMessage>> = db.whatsappMessageDao().loadAll()

    fun saveWhatsappMessageBot(whatsappMessageBot: WhatsappMessageBot) {
        appExecutors.diskIO().execute {
            db.whatsappMessageBotDao().save(whatsappMessageBot)
        }
    }

    fun findWhatsappMessagesBot(contact: String, message: String): List<WhatsappMessageBot> =
        db.whatsappMessageBotDao().find(contact, message)

    fun findAllWhatsappMessagesBot(): LiveData<List<WhatsappMessageBot>> =
        db.whatsappMessageBotDao().loadAll()
}