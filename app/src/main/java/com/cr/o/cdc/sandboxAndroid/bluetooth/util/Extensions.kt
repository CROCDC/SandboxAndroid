package com.cr.o.cdc.sandboxAndroid.bluetooth.util

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import androidx.core.content.ContextCompat

/**
 * Created by Cami on 11/21/20.
 */

fun Context.getBluetoothManager(): BluetoothManager? =
    ContextCompat.getSystemService<BluetoothManager>(
        this,
        BluetoothManager::class.java
    )

fun Context.getBluetoothAdapter(): BluetoothAdapter? = getBluetoothManager()?.adapter