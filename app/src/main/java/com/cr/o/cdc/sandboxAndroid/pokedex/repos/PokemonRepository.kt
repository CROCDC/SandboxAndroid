package com.cr.o.cdc.sandboxAndroid.pokedex.repos

import PokemonsQuery
import androidx.lifecycle.LiveData
import com.cr.o.cdc.networking.AppExecutors
import com.cr.o.cdc.networking.NetworkBoundResource
import com.cr.o.cdc.networking.NetworkResource
import com.cr.o.cdc.networking.NetworkResponse
import com.cr.o.cdc.sandboxAndroid.pokedex.db.model.Pokemon
import com.cr.o.cdc.sandboxAndroid.pokedex.db.model.PokemonMini
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.SandBoxDB
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    val dataSource: PokemonDataSourceProvider,
    val db: SandBoxDB,
    val appExecutors: AppExecutors
) {

    fun pokemons(first: Int): LiveData<NetworkResource<List<Pokemon>>> =
        object : NetworkBoundResource<List<Pokemon>, PokemonsQuery.Data>(appExecutors) {
            override fun saveCallResult(item: PokemonsQuery.Data?) {
                db.pokemonDao().saveAll(item?.pokemons?.mapNotNull { pokemon ->
                    pokemon?.let {
                        Pokemon(
                            pokemon.id,
                            pokemon.image,
                            pokemon.name,
                            pokemon.evolutions?.mapNotNull {
                                it?.let {
                                    PokemonMini(
                                        it.id,
                                        it.name
                                    )
                                }
                            }
                        )
                    }
                } ?: listOf())
            }

            override fun shouldFetch(data: List<Pokemon>?): Boolean = true

            override fun loadFromDb(): LiveData<List<Pokemon>> = db.pokemonDao().loadAll()

            override fun createCall(): LiveData<NetworkResponse<PokemonsQuery.Data>> =
                dataSource.pokemons(first)
        }.asLiveData()
}
