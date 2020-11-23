package com.cr.o.cdc.sandboxAndroid.bluetooth.model

import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothStatus

/**
 * Created by Cami on 11/23/20.
 */
data class BluetoothConnection(
    val message: String?,
    val status: BluetoothStatus?,
    val deviceName: String?
)