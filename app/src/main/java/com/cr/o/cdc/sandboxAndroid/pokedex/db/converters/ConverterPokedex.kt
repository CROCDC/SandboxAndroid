package com.cr.o.cdc.sandboxAndroid.pokedex.db.converters

import androidx.room.TypeConverter
import com.cr.o.cdc.sandboxAndroid.pokedex.db.model.PokemonMini
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class ConverterPokedex {

    @TypeConverter
    fun restoreEvolutions(listOfString: String?): List<PokemonMini>? = if (listOfString != null) {
        GsonBuilder().create()
            .fromJson(listOfString, object : TypeToken<List<PokemonMini>>() {}.type)
    } else {
        null
    }

    @TypeConverter
    fun saveEvolutions(list: List<PokemonMini>?): String = Gson().toJson(list)
}