package com.cr.o.cdc.sandboxAndroid.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cr.o.cdc.sandboxAndroid.db.dao.RecipeDao
import com.cr.o.cdc.sandboxAndroid.db.dao.WhatsappMessageAutoReplyDao
import com.cr.o.cdc.sandboxAndroid.db.dao.WhatsappMessageBotDao
import com.cr.o.cdc.sandboxAndroid.db.dao.WhatsappMessageDao
import com.cr.o.cdc.sandboxAndroid.pagination.model.InfoSearchRecipe
import com.cr.o.cdc.sandboxAndroid.pagination.model.Recipe
import com.cr.o.cdc.sandboxAndroid.whatsapputils.model.WhatsappMessageAutoReply
import com.cr.o.cdc.sandboxAndroid.whatsapputils.model.WhatsappMessage
import com.cr.o.cdc.sandboxAndroid.whatsapputils.model.WhatsappMessageBot

@Database(
    entities = [Recipe::class, InfoSearchRecipe::class, WhatsappMessage::class, WhatsappMessageBot::class, WhatsappMessageAutoReply::class],
    version = 3
)
abstract class SandBoxDB : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    abstract fun whatsappMessageDao(): WhatsappMessageDao

    abstract fun whatsappMessageBotDao(): WhatsappMessageBotDao

    abstract fun whatsappAutoReplyDao(): WhatsappMessageAutoReplyDao

    companion object {
        const val DATABASE_NAME = "sandbox"
    }

}