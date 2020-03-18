package com.cr.o.cdc.sandboxAndroid.pokedex.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pokemon(
    @PrimaryKey
    val id: String,
    val image: String?,
    val name: String?,
    val evolutions: List<PokemonMini>?
)
