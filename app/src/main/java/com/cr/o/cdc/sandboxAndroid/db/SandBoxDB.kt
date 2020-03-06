package com.cr.o.cdc.sandboxAndroid.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cr.o.cdc.sandboxAndroid.db.dao.RecipeDao
import com.cr.o.cdc.sandboxAndroid.db.dao.WhatsappMessageDao
import com.cr.o.cdc.sandboxAndroid.pagination.model.InfoSearchRecipe
import com.cr.o.cdc.sandboxAndroid.pagination.model.Recipe
import com.cr.o.cdc.sandboxAndroid.whatsapputils.model.WhatsappMessage

@Database(entities = [Recipe::class, InfoSearchRecipe::class, WhatsappMessage::class], version = 2)
abstract class SandBoxDB : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    abstract fun whatsappMessageDao(): WhatsappMessageDao

    companion object {
        const val DATABASE_NAME = "sandbox"
    }

}