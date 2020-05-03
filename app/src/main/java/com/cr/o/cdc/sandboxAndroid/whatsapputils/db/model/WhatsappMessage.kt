package com.cr.o.cdc.sandboxAndroid.whatsapputils.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WhatsappMessage(@PrimaryKey val message: String, val contact: String?)
