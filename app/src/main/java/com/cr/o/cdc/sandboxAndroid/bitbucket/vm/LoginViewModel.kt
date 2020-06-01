package com.cr.o.cdc.sandboxAndroid.bitbucket.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cr.o.cdc.networking.NetworkResource
import com.cr.o.cdc.sandboxAndroid.bitbucket.model.LoginResponse
import com.cr.o.cdc.sandboxAndroid.bitbucket.repos.BitbucketAuthRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: BitbucketAuthRepository) :
    ViewModel() {

    fun login(
        key: String,
        secret: String,
        username: String,
        password: String
    ): LiveData<NetworkResource<LoginResponse>> =
        repository.login(key, secret, username, password)
}