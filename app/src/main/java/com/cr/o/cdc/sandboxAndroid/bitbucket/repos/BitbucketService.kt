package com.cr.o.cdc.sandboxAndroid.bitbucket.repos

import androidx.lifecycle.LiveData
import com.cr.o.cdc.networking.NetworkResponse
import com.cr.o.cdc.sandboxAndroid.bitbucket.model.PaginatedRepositoriesResponse
import com.cr.o.cdc.sandboxAndroid.bitbucket.model.PaginatedWorkspaceResponse
import com.cr.o.cdc.sandboxAndroid.bitbucket.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BitbucketService {

    @GET("user")
    fun user(): LiveData<NetworkResponse<UserResponse>>

    @GET("workspaces")
    fun workspaces(): LiveData<NetworkResponse<PaginatedWorkspaceResponse>>

    @GET("repositories/{slug}")
    fun repositories(
        @Path("slug") workspaceUIDD: String
    ): LiveData<NetworkResponse<PaginatedRepositoriesResponse>>
}
