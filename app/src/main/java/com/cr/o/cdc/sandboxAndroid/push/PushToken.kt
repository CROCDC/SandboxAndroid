package com.cr.o.cdc.sandboxAndroid.push

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.cr.o.cdc.sandboxAndroid.editAndApply
import javax.inject.Inject

/**
 * Created by Camilo on 22/01/20.
 */

class PushToken @Inject constructor(private val sharedPreferences: SharedPreferences) :
    LiveData<String>() {

    private val listener: SharedPreferences.OnSharedPreferenceChangeListener

    init {
        postValue(sharedPreferences.getString(PROPERTY_PUSH_TOKEN, null))

        listener = SharedPreferences.OnSharedPreferenceChangeListener { pref, key ->
            if (PROPERTY_PUSH_TOKEN == key) {
                postValue(pref.getString(PROPERTY_PUSH_TOKEN, null))
            }
        }
    }

    override fun onActive() {
        super.onActive()
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun onInactive() {
        super.onInactive()
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
    }

    fun savePushToken(token: String) {
        sharedPreferences.editAndApply {
            putString(PROPERTY_PUSH_TOKEN, token)
        }
    }

    companion object {
        private const val PROPERTY_PUSH_TOKEN = "push_token"
    }
}