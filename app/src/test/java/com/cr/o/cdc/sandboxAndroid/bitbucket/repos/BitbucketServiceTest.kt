package com.cr.o.cdc.sandboxAndroid.bitbucket.repos

import com.cr.o.cdc.networking.SuccessResponse
import com.cr.o.cdc.sandboxAndroid.bitbucket.di.AppModuleBitbucket
import com.cr.o.cdc.sandboxAndroid.bitbucket.model.PaginatedRepositoriesResponse
import com.cr.o.cdc.sandboxAndroid.bitbucket.model.PaginatedWorkspaceResponse
import com.cr.o.cdc.sandboxAndroid.utils.DummySharedPreferences
import com.cr.o.cdc.sandboxAndroid.utils.EndpointTest
import com.cr.o.cdc.sharedtest.getValue
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

class BitbucketServiceTest : EndpointTest() {

    private lateinit var service: BitbucketService

    @Before
    fun initService() {
        service = AppModuleBitbucket().provideBitbucketService(
            Retrofit.Builder(),
            DummySharedPreferences(getAccessTokenBitbucket())
        )
    }


    @Test
    fun user() {
        val response = getValue(service.user())

        assertTrue(response is SuccessResponse)
    }

    @Test
    fun workspaces() {
        val response = getValue(service.workspaces())

        assertTrue(response is SuccessResponse)

        print(response as SuccessResponse<PaginatedWorkspaceResponse>)
    }

    @Test
    fun repositories() {
        val uuid =
            (getValue(service.workspaces()) as SuccessResponse<PaginatedWorkspaceResponse>)
                .data!!.values[0].slug
        val response = getValue(service.repositories(uuid))

        assertTrue(response is SuccessResponse)

        print(response as SuccessResponse<PaginatedRepositoriesResponse>)
    }

}
