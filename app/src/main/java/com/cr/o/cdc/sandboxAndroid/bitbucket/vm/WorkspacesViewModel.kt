package com.cr.o.cdc.sandboxAndroid.bitbucket.vm

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cr.o.cdc.networking.NetworkResource
import com.cr.o.cdc.sandboxAndroid.bitbucket.model.Workspace
import com.cr.o.cdc.sandboxAndroid.bitbucket.repos.BitbucketAuthService
import com.cr.o.cdc.sandboxAndroid.bitbucket.repos.BitbucketRepository
import javax.inject.Inject

class WorkspacesViewModel @Inject constructor(
    repository: BitbucketRepository,
    private val sharedPreferences: SharedPreferences
) :
    ViewModel() {

    val workspaces: LiveData<NetworkResource<List<Workspace>>> = repository.workspace()

    fun logout() {
        sharedPreferences.edit().putString(BitbucketAuthService.ACCESS_TOKEN, null)
            .putString(BitbucketAuthService.REFRESH_TOKEN, null).apply()
    }
}