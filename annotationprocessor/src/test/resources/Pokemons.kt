package com.cr.o.cdc.sandboxAndroid.pokemons.model

import com.cr.o.cdc.annotations.GraphQlRequest
import com.cr.o.cdc.annotations.Input

@GraphQlRequest("", "pokemons", [Input("first", Int::class,false)], true)
data class Pokemons(val pokemons: List<Pokemon>)

@GraphQlRequest("", "pokemon", [Input("name", String::class, true)], true)
data class Pokemon(
    val id: String,
    val name: String,
    val weight: PokemonDimension,
    val evolutions: List<PokemonMini>
)

class PokemonMini(
    val id: String,
    val name: String
)

data class PokemonDimension(
    val minimum: String,
    val maximum: String
)