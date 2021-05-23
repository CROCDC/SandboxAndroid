package com.cr.o.cdc.sandboxAndroid.buitresenal.util

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.cr.o.cdc.sandboxAndroid.editAndApply
import javax.inject.Inject

/**
 * Created by Cami on 5/22/21.
 */
class ObservableInstagramUserInfo @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : LiveData<InstagramUserInfo>() {

    private val listener: SharedPreferences.OnSharedPreferenceChangeListener

    init {
        postValue(getInstagramUserInfo())

        listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (PROPERTY_ACCESS_TOKEN == key || PROPERTY_USER_ID == key) {
                postValue(getInstagramUserInfo())
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

    fun getInstagramUserInfo(): InstagramUserInfo? {
        val accessToken = sharedPreferences.getString(PROPERTY_ACCESS_TOKEN, null)
        val userId = sharedPreferences.getString(PROPERTY_USER_ID, null)
        return if (userId != null && accessToken != null) {
            InstagramUserInfo(accessToken, userId)
        } else {
            null
        }
    }

    fun saveAccessToken(accessToken: String) {
        sharedPreferences.editAndApply {
            putString(PROPERTY_ACCESS_TOKEN, accessToken)
        }
    }

    fun saveUserId(userId: String) {
        sharedPreferences.editAndApply {
            putString(PROPERTY_USER_ID, userId)
        }
    }

    companion object {
        private const val PROPERTY_ACCESS_TOKEN = "access_token"
        private const val PROPERTY_USER_ID = "user_id"
    }
}

data class InstagramUserInfo(
    val accessToken: String,
    val userId: String
)