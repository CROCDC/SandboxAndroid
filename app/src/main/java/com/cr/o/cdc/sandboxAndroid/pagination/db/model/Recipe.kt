package com.cr.o.cdc.sandboxAndroid.pagination.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    @PrimaryKey val uri: String,
    val label: String
)
