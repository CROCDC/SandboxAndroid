package com.cr.o.cdc.sandboxAndroid.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.push.PushToken
import javax.inject.Inject

/**
 * Created by Camilo on 23/01/20.
 */
class NotificationsViewModel @Inject constructor(pushToken: PushToken) : ViewModel() {

    val token: LiveData<String> = pushToken
}