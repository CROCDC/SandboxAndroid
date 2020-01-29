package com.cr.o.cdc.sandboxAndroid.model

import com.cr.o.cdc.requestsannotations.GraphQlRequest

/**
 * Created by Camilo on 01/01/20.
 */

@GraphQlRequest
data class Pokemon(
    val id: String,
    val name: String,
    val weight: PokemonDimension,
    val evolutions: List<PokemonMini>
)