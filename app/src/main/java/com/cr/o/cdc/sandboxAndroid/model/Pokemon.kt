package com.cr.o.cdc.sandboxAndroid.model

import com.cr.o.cdc.annotations.GraphQlRequest
import com.cr.o.cdc.requestsannotations.Input
import com.cr.o.cdc.sandboxAndroid.BuildConfig

/**
 * Created by Camilo on 01/01/20.
 */

@GraphQlRequest(BuildConfig.URL_SERVER, "pokemon", [Input("name", String::class)], true)
data class Pokemon(
    val id: String,
    val name: String,
    val weight: PokemonDimension,
    val evolutions: List<PokemonMini>
)
