package com.cr.o.cdc.sandboxAndroid.whatsapputils.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WhatsappMessageAutoReply(@PrimaryKey val message: String, val active: Boolean)
