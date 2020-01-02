package com.cr.o.cdc.requestbuilder

import org.junit.Test

/**
 * Created by Camilo on 01/01/20.
 */
class KotlinClassBuilder {

    @Test
    fun classNotFound() {

    }
}

data class Pokemon(
    val id: String,
    val name: String,
    val weight: PokemonDimension
)

data class PokemonDimension(
    val minimum: String,
    val maximum: String
)