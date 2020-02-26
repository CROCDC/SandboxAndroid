package com.cr.o.cdc.sandboxAndroid.whatsapputils.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WhatsappMessage(@PrimaryKey(autoGenerate = true) val id: Int, val message: String?, val contact: String?)