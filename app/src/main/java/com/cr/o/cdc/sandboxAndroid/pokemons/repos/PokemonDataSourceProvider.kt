package com.cr.o.cdc.sandboxAndroid.pokemons.repos

import androidx.lifecycle.LiveData
import com.cr.o.cdc.requestsmodule.Response
import com.cr.o.cdc.sandboxAndroid.pokemons.model.Pokemon

/**
 * Created by Camilo on 31/12/19.
 */
interface PokemonDataSourceProvider {
    fun pokemon(name: String): LiveData<Response<Pokemon?>>
}