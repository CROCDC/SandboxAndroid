package com.cr.o.cdc.sandboxAndroid.whatsapputils.services

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import androidx.core.app.RemoteInput
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.model.WhatsappMessage
import com.cr.o.cdc.sandboxAndroid.whatsapputils.repos.WhatsappMessagesRepository
import com.cr.o.cdc.sandboxAndroid.whatsapputils.vo.WhatsappConfig
import dagger.android.AndroidInjection
import javax.inject.Inject

class MyNotificationListenerService : NotificationListenerService() {

    @Inject
    lateinit var repository: WhatsappMessagesRepository

    @Inject
    lateinit var whatsappConfig: WhatsappConfig

    override fun onCreate() {
        super.onCreate()
        AndroidInjection.inject(this)
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        super.onNotificationPosted(sbn)
        if (sbn.key.contains("com.whatsapp")) {
            val notification = sbn.notification
            val contact = sbn.notification.extras.getString("android.title")
            val message = notification.extras.getString("android.text")

            if (message != null && contact != null) {
                repository.saveWhatsappMessage(WhatsappMessage(message, contact))
                if (whatsappConfig.enable) {
                    repository.findWhatsappMessagesBot(contact, message).forEach { bot ->
                        val intent = Intent()
                        val bundle = Bundle()

                        sbn.notification.actions.find { filtering ->
                            filtering.remoteInputs.find {
                                it.resultKey.contains(
                                    "reply",
                                    false
                                ) || it.resultKey.contains("android.intent.extra.text", false)
                            } != null
                        }?.let { action ->
                            val pendingIntent = action.actionIntent
                            try {
                                RemoteInput.addResultsToIntent(action.remoteInputs?.map {
                                    bundle.putCharSequence(it.resultKey, bot.message)
                                    RemoteInput.Builder(it.resultKey).apply {
                                        setLabel(it.label)
                                        setChoices(it.choices)
                                        setAllowFreeFormInput(it.allowFreeFormInput)
                                        addExtras(it.extras)
                                    }.build()
                                }?.toTypedArray(), intent, bundle)
                                pendingIntent.send(applicationContext, 0, intent)
                            } catch (e: PendingIntent.CanceledException) {
                            }
                        }
                    }
                }
            }
        }
    }
}
