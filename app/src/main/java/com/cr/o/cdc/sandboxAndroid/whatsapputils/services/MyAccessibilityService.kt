package com.cr.o.cdc.sandboxAndroid.whatsapputils.services

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class MyAccessibilityService : AccessibilityService() {
    override fun onInterrupt() {}

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        if (event.packageName == "com.whatsapp") {
            Log.e("CROCDC", event.toString())
        }
    }

}