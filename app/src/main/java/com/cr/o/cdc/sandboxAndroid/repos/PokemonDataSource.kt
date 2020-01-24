package com.cr.o.cdc.sandboxAndroid.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cr.o.cdc.requests.Manager
import com.cr.o.cdc.requests.NetworkResponse
import com.cr.o.cdc.sandboxAndroid.model.Pokemon

class PokemonDataSource(private val manager: Manager) : PokemonDataSourceProvider {

    override fun pokemon(id: String): LiveData<NetworkResponse<Pokemon>> =
        MutableLiveData()
}
