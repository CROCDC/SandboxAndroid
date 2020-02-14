package com.cr.o.cdc.sandboxAndroid.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cr.o.cdc.sandboxAndroid.db.dao.RecipeDao
import com.cr.o.cdc.sandboxAndroid.pagination.model.InfoSearchRecipe
import com.cr.o.cdc.sandboxAndroid.pagination.model.Recipe

@Database(entities = [Recipe::class, InfoSearchRecipe::class], version = 1)
abstract class SandBoxDB : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    companion object {
        const val DATABASE_NAME = "sandbox"
    }

}