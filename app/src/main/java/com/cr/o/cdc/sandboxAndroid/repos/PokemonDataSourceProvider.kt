package com.cr.o.cdc.sandboxAndroid.repos

import androidx.lifecycle.LiveData
import com.cr.o.cdc.requests.Response
import com.cr.o.cdc.sandboxAndroid.model.Pokemon

/**
 * Created by Camilo on 31/12/19.
 */
interface PokemonDataSourceProvider {
    fun pokemon(name: String): LiveData<Response<Pokemon?>>
}