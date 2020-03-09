package com.cr.o.cdc.sandboxAndroid.services

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import androidx.core.app.RemoteInput
import com.cr.o.cdc.sandboxAndroid.whatsapputils.model.WhatsappMessage
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
            if (notification.category != "msg") {
                notification.extras.getString("android.text")?.let {
                    repository.saveWhatsappMessage(
                        WhatsappMessage(
                            it,
                            sbn.notification.extras.getString("android.title")
                        )
                    )
                }
                if (whatsappConfig.enable) {
                    val msg = "H"
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
                                bundle.putCharSequence(it.resultKey, msg)
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