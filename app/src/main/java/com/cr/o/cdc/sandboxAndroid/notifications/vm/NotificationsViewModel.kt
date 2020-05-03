package com.cr.o.cdc.sandboxAndroid.notifications.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.notifications.vo.PushToken
import javax.inject.Inject

class NotificationsViewModel @Inject constructor(pushToken: PushToken) : ViewModel() {

    val token: LiveData<String> = pushToken
}
