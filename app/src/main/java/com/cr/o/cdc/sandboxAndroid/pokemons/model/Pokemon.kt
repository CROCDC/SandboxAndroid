package com.cr.o.cdc.sandboxAndroid.pokemons.model

data class Pokemon(
    val id: String,
    val name: String,
    val weight: PokemonDimension,
    val evolutions: List<PokemonMini>
)
