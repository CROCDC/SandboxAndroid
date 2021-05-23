package com.cr.o.cdc.sandboxAndroid.buitresenal.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cr.o.cdc.sandboxAndroid.buitresenal.db.model.InstagramUser
import com.cr.o.cdc.sandboxAndroid.buitresenal.model.AccessTokenResponse
import com.cr.o.cdc.sandboxAndroid.buitresenal.util.InstagramApiInfo
import com.cr.o.cdc.sandboxAndroid.buitresenal.util.ObservableInstagramUserInfo
import com.cr.o.cdc.sandboxAndroid.libraries.networking.AppExecutors
import com.cr.o.cdc.sandboxAndroid.libraries.networking.NetworkBoundResource
import com.cr.o.cdc.sandboxAndroid.libraries.networking.NetworkResource
import com.cr.o.cdc.sandboxAndroid.libraries.networking.NetworkResponse
import com.cr.o.cdc.sandboxAndroid.libraries.networking.SimpleNetworkBoundResource
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.SandBoxDB
import javax.inject.Inject

/**
 * Created by Cami on 5/22/21.
 */
class InstagramRepository @Inject constructor(
    private val apiInstagramService: ApiInstagramService,
    private val graphInstagramService: GraphInstagramService,
    private val appExecutors: AppExecutors,
    private val observableInstagramUserInfo: ObservableInstagramUserInfo,
    private val db: SandBoxDB
) {

    fun getAccessToken(code: String): LiveData<NetworkResource<AccessTokenResponse>> =
        object : SimpleNetworkBoundResource<AccessTokenResponse>(appExecutors) {
            override fun saveCallResult(item: AccessTokenResponse?) {
                item?.let {
                    observableInstagramUserInfo.saveAccessToken(it.access_token)
                    observableInstagramUserInfo.saveUserId(it.user_id)
                }
            }

            override fun createCall(): LiveData<NetworkResponse<AccessTokenResponse>> =
                apiInstagramService.getAccessToken(
                    InstagramApiInfo.CLIENT_ID,
                    InstagramApiInfo.APP_SECRET,
                    "authorization_code",
                    InstagramApiInfo.REDIRECT_URI,
                    code
                )
        }.asLiveData()

    fun getUser(userId: String): LiveData<NetworkResource<InstagramUser>> {
        val accessToken = observableInstagramUserInfo.getInstagramUserInfo()?.accessToken
            ?: return MutableLiveData(NetworkResource.error())
        return object : NetworkBoundResource<InstagramUser, InstagramUser>(appExecutors) {
            override fun saveCallResult(item: InstagramUser?) {
                item?.let { db.instagramUserDaoUserDao().save(it) }
            }

            override fun shouldFetch(data: InstagramUser?): Boolean = true

            override fun loadFromDb(): LiveData<InstagramUser> =
                db.instagramUserDaoUserDao().loadById(userId)


            override fun createCall(): LiveData<NetworkResponse<InstagramUser>> =
                graphInstagramService.getUser(
                    userId,
                    "id,username",
                    accessToken
                )
        }.asLiveData()
    }

}
