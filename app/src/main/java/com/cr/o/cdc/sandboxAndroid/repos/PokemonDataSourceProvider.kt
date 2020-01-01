package com.cr.o.cdc.sandboxAndroid.repos

import PokemonQuery
import androidx.lifecycle.LiveData
import com.cr.o.cdc.requests.NetworkResponse

/**
 * Created by Camilo on 31/12/19.
 */
interface PokemonDataSourceProvider {
    fun pokemon(id: String): LiveData<com.cr.o.cdc.requests.NetworkResponse<PokemonQuery.Data>>
}