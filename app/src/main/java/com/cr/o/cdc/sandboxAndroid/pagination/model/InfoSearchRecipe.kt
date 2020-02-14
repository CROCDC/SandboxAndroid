package com.cr.o.cdc.sandboxAndroid.pagination.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "info_search_recipe", primaryKeys = ["recipe_uri", "search"])
data class InfoSearchRecipe(
    @ColumnInfo(name = "recipe_uri") val uri: String,
    val from: Int,
    @ColumnInfo(name = "search") val search: String
)
