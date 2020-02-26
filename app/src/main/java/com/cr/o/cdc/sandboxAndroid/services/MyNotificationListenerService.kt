package com.cr.o.cdc.sandboxAndroid.services

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.cr.o.cdc.sandboxAndroid.whatsapputils.model.WhatsappMessage
import com.cr.o.cdc.sandboxAndroid.whatsapputils.repos.WhatsappMessagesRepository
import dagger.android.AndroidInjection
import javax.inject.Inject

class MyNotificationListenerService : NotificationListenerService() {

    @Inject
    lateinit var repository: WhatsappMessagesRepository

    override fun onCreate() {
        super.onCreate()
        AndroidInjection.inject(this)
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        super.onNotificationPosted(sbn)
        if (sbn.key.contains("com.whatsapp")) {
            repository.saveWhatsappMesagge(
                WhatsappMessage(
                    0,
                    sbn.notification.extras.getString("android.text"),
                    sbn.notification.extras.getString("android.title")
                )
            )
        }
    }
}