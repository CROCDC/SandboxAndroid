package com.cr.o.cdc.sandboxAndroid.whatsapputils.vo

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import javax.inject.Inject

class WhatsappConfig @Inject constructor(private val sharedPreferences: SharedPreferences) :
    LiveData<WhatsappConfigInfo>() {

    private val listener: SharedPreferences.OnSharedPreferenceChangeListener

    init {
        postValue(fromSharedPreferences())

        listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (PROPERTY_ENABLE == key) {
                postValue(fromSharedPreferences())
            }
        }
    }

    val enable: Boolean
        get() = sharedPreferences.getBoolean(PROPERTY_ENABLE, false)

    private fun fromSharedPreferences() = WhatsappConfigInfo(
        sharedPreferences.getBoolean(PROPERTY_ENABLE, false)
    )


    companion object {
        const val PROPERTY_ENABLE = "property_enable"
    }
}


data class WhatsappConfigInfo(val enable: Boolean)