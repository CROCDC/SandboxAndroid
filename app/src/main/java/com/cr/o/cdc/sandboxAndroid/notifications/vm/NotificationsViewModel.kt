package com.cr.o.cdc.sandboxAndroid.notifications.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.notifications.vo.PushToken

class NotificationsViewModel @ViewModelInject constructor(pushToken: PushToken) : ViewModel() {

    val token: LiveData<String> = pushToken
}
