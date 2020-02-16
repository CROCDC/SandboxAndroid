package com.cr.o.cdc.sandboxAndroid.pokemons.model

import com.cr.o.cdc.annotations.GraphQlRequest
import com.cr.o.cdc.annotations.Input
import com.cr.o.cdc.sandboxAndroid.BuildConfig

@GraphQlRequest(BuildConfig.URL_SERVER, "pokemons", [Input("first", Int::class, false)], true)
data class Pokemons(val pokemons: List<Pokemon>)