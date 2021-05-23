package com.cr.o.cdc.sandboxAndroid.buitresenal.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.cr.o.cdc.sandboxAndroid.buitresenal.db.model.InstagramUser
import com.cr.o.cdc.sandboxAndroid.buitresenal.repos.InstagramRepository
import com.cr.o.cdc.sandboxAndroid.libraries.networking.NetworkResource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Cami on 5/22/21.
 */
@HiltViewModel
class SuccessLoginViewModel @Inject constructor(
    private val repository: InstagramRepository
) : ViewModel() {

    fun getUser(code: String): LiveData<NetworkResource<InstagramUser>> =
        repository.getAccessToken(code).switchMap { r ->
            r.data?.let {
                repository.getUser(it.user_id)
            } ?: MutableLiveData()
        }
}
