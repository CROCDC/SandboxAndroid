package com.cr.o.cdc.sandboxAndroid.whatsapputils.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WhatsappAutoReply(@PrimaryKey val message: String)