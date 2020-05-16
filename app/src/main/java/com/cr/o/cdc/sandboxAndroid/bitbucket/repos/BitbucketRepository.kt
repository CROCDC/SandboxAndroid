package com.cr.o.cdc.sandboxAndroid.bitbucket.repos

import androidx.lifecycle.LiveData
import com.cr.o.cdc.networking.AppExecutors
import com.cr.o.cdc.networking.NetworkBoundResource
import com.cr.o.cdc.networking.NetworkResource
import com.cr.o.cdc.networking.NetworkResponse
import com.cr.o.cdc.sandboxAndroid.bitbucket.model.PaginatedWorkspaceResponse
import com.cr.o.cdc.sandboxAndroid.bitbucket.model.Workspace
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.SandBoxDB
import javax.inject.Inject

class BitbucketRepository @Inject constructor(
    val service: BitbucketService,
    val db: SandBoxDB,
    val appExecutors: AppExecutors
) {


    fun workspace(): LiveData<NetworkResource<List<Workspace>>> =
        object : NetworkBoundResource<List<Workspace>, PaginatedWorkspaceResponse>(appExecutors) {
            override fun saveCallResult(item: PaginatedWorkspaceResponse?) {
                db.workspaceDao().saveAll(item?.values)
            }

            override fun shouldFetch(data: List<Workspace>?): Boolean = true

            override fun loadFromDb(): LiveData<List<Workspace>> = db.workspaceDao().loadAll()

            override fun createCall(): LiveData<NetworkResponse<PaginatedWorkspaceResponse>> =
                service.workspaces()

        }.asLiveData()
}
