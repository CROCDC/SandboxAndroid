package com.cr.o.cdc.sandboxAndroid.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cr.o.cdc.sandboxAndroid.coronavirus.dao.CountryStatDao
import com.cr.o.cdc.sandboxAndroid.coronavirus.model.CountryStat
import com.cr.o.cdc.sandboxAndroid.db.dao.RecipeDao
import com.cr.o.cdc.sandboxAndroid.db.dao.WhatsappMessageAutoReplyDao
import com.cr.o.cdc.sandboxAndroid.db.dao.WhatsappMessageBotDao
import com.cr.o.cdc.sandboxAndroid.db.dao.WhatsappMessageDao
import com.cr.o.cdc.sandboxAndroid.pagination.model.InfoSearchRecipe
import com.cr.o.cdc.sandboxAndroid.pagination.model.Recipe
import com.cr.o.cdc.sandboxAndroid.pokedex.db.converters.ConverterPokedex
import com.cr.o.cdc.sandboxAndroid.pokedex.db.dao.PokemonDao
import com.cr.o.cdc.sandboxAndroid.pokedex.db.model.Pokemon
import com.cr.o.cdc.sandboxAndroid.whatsapputils.model.WhatsappMessage
import com.cr.o.cdc.sandboxAndroid.whatsapputils.model.WhatsappMessageAutoReply
import com.cr.o.cdc.sandboxAndroid.whatsapputils.model.WhatsappMessageBot

@Database(
    entities = [
        Recipe::class, InfoSearchRecipe::class, WhatsappMessage::class, WhatsappMessageBot::class,
        WhatsappMessageAutoReply::class, CountryStat::class, Pokemon::class],
    version = 6
)
@TypeConverters(ConverterPokedex::class)
abstract class SandBoxDB : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    abstract fun whatsappMessageDao(): WhatsappMessageDao

    abstract fun whatsappMessageBotDao(): WhatsappMessageBotDao

    abstract fun whatsappAutoReplyDao(): WhatsappMessageAutoReplyDao

    abstract fun countryStatDao(): CountryStatDao

    abstract fun pokemonDao(): PokemonDao

    companion object {
        const val DATABASE_NAME = "sandbox"
    }

}