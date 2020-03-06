package com.cr.o.cdc.sandboxAndroid.services

import android.app.PendingIntent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.cr.o.cdc.sandboxAndroid.services.utils.NotificationUtils
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
            val notification = sbn.notification
            if (notification.category != "msg") {
                notification.extras.getString("android.text")?.let {
                    repository.saveWhatsappMesagge(
                        WhatsappMessage(
                            it,
                            sbn.notification.extras.getString("android.title")
                        )
                    )
                }
                //todo logic
                if (false) {
                    val action =
                        NotificationUtils.getQuickReplyAction(sbn.notification, packageName)

                    try {
                        action.sendReply(applicationContext, "HOLA")
                    } catch (e: PendingIntent.CanceledException) {
                    }
                }
            }
        }

    }
}