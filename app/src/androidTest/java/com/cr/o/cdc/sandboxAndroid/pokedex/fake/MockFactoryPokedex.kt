package com.cr.o.cdc.sandboxAndroid.pokedex.fake

import com.cr.o.cdc.sandboxAndroid.pokedex.db.model.Pokemon
import com.cr.o.cdc.sandboxAndroid.pokedex.db.model.PokemonMini

object MockFactoryPokedex {

    private fun getPokemon(): Pokemon = Pokemon(
        "id1",
        null,
        null,
        listOf(PokemonMini("id", "name"))
    )

    fun getListOfPokemons(): List<Pokemon> = listOf(getPokemon())
}
