package com.cr.o.cdc.sandboxAndroid.bitbucket.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cr.o.cdc.networking.NetworkResource
import com.cr.o.cdc.sandboxAndroid.bitbucket.model.Workspace
import com.cr.o.cdc.sandboxAndroid.bitbucket.repos.BitbucketRepository
import javax.inject.Inject

class WorkspacesViewModel @Inject constructor(private val repository: BitbucketRepository) :
    ViewModel() {

    val workspaces: LiveData<NetworkResource<List<Workspace>>> = repository.workspace()

}