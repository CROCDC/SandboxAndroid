package com.cr.o.cdc.sandboxAndroid.buitresenal.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InstagramUser(
    @PrimaryKey
    val id: String,
    val username: String
)
