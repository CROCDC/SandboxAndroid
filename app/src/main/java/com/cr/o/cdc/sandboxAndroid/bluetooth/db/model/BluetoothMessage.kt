package com.cr.o.cdc.sandboxAndroid.bluetooth.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Cami on 11/22/20.
 */
@Entity
data class BluetoothMessage(
    @PrimaryKey
    val message: String
)