package com.cr.o.cdc.sandboxAndroid.whatsapputils.repos

import androidx.lifecycle.LiveData
import com.cr.o.cdc.requests.AppExecutors
import com.cr.o.cdc.sandboxAndroid.db.SandBoxDB
import com.cr.o.cdc.sandboxAndroid.whatsapputils.model.WhatsappMessage
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
}