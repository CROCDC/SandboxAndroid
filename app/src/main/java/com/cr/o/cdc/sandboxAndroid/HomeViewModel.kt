package com.cr.o.cdc.sandboxAndroid

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.bitbucket.repos.BitbucketAuthService
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val sharedPreferences: SharedPreferences) :
    ViewModel() {

    fun isLoginInBitbucket(): Boolean =
        sharedPreferences.getString(BitbucketAuthService.ACCESS_TOKEN, null) != null
}