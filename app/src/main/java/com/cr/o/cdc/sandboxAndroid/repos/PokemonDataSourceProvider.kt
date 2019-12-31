package com.cr.o.cdc.sandboxAndroid.repos

import PokemonQuery
import androidx.lifecycle.LiveData
import com.cr.o.cdc.sandboxAndroid.network.NetworkResponse

/**
 * Created by Camilo on 31/12/19.
 */
interface PokemonDataSourceProvider {
    fun pokemon(id: String): LiveData<NetworkResponse<PokemonQuery.Data>>
}