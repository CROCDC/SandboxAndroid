package com.cr.o.cdc.sandboxAndroid.whatsapputils.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WhatsappMessageBot(
    val contact: String,
    val response: String,
    @PrimaryKey val message: String
)