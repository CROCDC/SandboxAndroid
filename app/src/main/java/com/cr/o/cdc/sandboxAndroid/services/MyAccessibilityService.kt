package com.cr.o.cdc.sandboxAndroid.services

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import com.cr.o.cdc.sandboxAndroid.childs

class MyAccessibilityService : AccessibilityService() {
    override fun onInterrupt() {}
    private var lastQuestion: String? = null
    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        when (event.packageName) {
            "com.etermax.preguntados.lite" -> {
                if (event.source != null) {
                    getQuestion(event.source)?.let {
                        if (lastQuestion != it || lastQuestion == null) {
                            lastQuestion = it
                            Log.e("Pregunta:", it)
                        }
                    }
                }
            }
        }
    }

    private fun getQuestion(event: AccessibilityNodeInfo): String? =
        event.childs().mapNotNull {
            if (it.className != null && it.className == "android.widget.TextView"
                && it.text != null && it.text.contains("?")
            ) {
                it.text.toString()
            } else {
                getQuestion(it)
            }
        }.getOrNull(0)


}
