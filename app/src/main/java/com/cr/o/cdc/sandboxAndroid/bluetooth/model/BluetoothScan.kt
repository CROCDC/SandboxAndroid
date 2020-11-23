package com.cr.o.cdc.sandboxAndroid.bluetooth.model

import android.bluetooth.BluetoothDevice

/**
 * Created by Cami on 11/21/20.
 */
data class BluetoothScan(val isScanning: Boolean, val devices: Set<BluetoothDevice>)
