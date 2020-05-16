package com.cr.o.cdc.sandboxAndroid.whatsapputils.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cr.o.cdc.sandboxAndroid.bitbucket.db.dao.WorkspaceDao
import com.cr.o.cdc.sandboxAndroid.bitbucket.model.Workspace
import com.cr.o.cdc.sandboxAndroid.coronavirus.db.dao.CountryStatDao
import com.cr.o.cdc.sandboxAndroid.coronavirus.db.model.CountryStat
import com.cr.o.cdc.sandboxAndroid.downdetector.db.dao.SiteDao
import com.cr.o.cdc.sandboxAndroid.downdetector.db.model.Site
import com.cr.o.cdc.sandboxAndroid.pagination.db.dao.RecipeDao
import com.cr.o.cdc.sandboxAndroid.pagination.db.model.InfoSearchRecipe
import com.cr.o.cdc.sandboxAndroid.pagination.db.model.Recipe
import com.cr.o.cdc.sandboxAndroid.pokedex.db.converters.ConverterPokedex
import com.cr.o.cdc.sandboxAndroid.pokedex.db.dao.PokemonDao
import com.cr.o.cdc.sandboxAndroid.pokedex.db.model.Pokemon
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.dao.WhatsappMessageAutoReplyDao
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.dao.WhatsappMessageBotDao
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.dao.WhatsappMessageDao
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.model.WhatsappMessage
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.model.WhatsappMessageAutoReply
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.model.WhatsappMessageBot

@Database(
    entities = [
        Recipe::class, InfoSearchRecipe::class, WhatsappMessage::class, WhatsappMessageBot::class,
        WhatsappMessageAutoReply::class, CountryStat::class, Pokemon::class, Site::class,
        Workspace::class],
    version = 7
)
@TypeConverters(ConverterPokedex::class)
abstract class SandBoxDB : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    abstract fun whatsappMessageDao(): WhatsappMessageDao

    abstract fun whatsappMessageBotDao(): WhatsappMessageBotDao

    abstract fun whatsappAutoReplyDao(): WhatsappMessageAutoReplyDao

    abstract fun countryStatDao(): CountryStatDao

    abstract fun pokemonDao(): PokemonDao

    abstract fun siteDao(): SiteDao

    abstract fun workspaceDao(): WorkspaceDao

    companion object {
        const val DATABASE_NAME = "sandbox"
    }
}
