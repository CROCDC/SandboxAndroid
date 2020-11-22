package com.cr.o.cdc.sandboxAndroid.bluetooth.repos

import android.bluetooth.BluetoothDevice
import android.content.Context
import androidx.lifecycle.LiveData
import com.cr.o.cdc.sandboxAndroid.bluetooth.db.model.BluetoothMessage
import com.cr.o.cdc.sandboxAndroid.bluetooth.util.getBluetoothManager
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.SandBoxDB
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Cami on 11/22/20.
 */
@Singleton
class BluetoothRepository @Inject constructor(private val db: SandBoxDB, @ApplicationContext context: Context) {

    private val bluetoothService: BluetoothService? =
        context.getBluetoothManager()?.adapter?.let { BluetoothService(it) }

    fun connect(device: BluetoothDevice) {
        bluetoothService?.connect(device)
    }

    fun sendMessage(message: String, macAddress: String) {
        bluetoothService?.sendMessage(message, macAddress)
    }

    fun addBluetoothMessage(message: String) {
        db.bluetoothMessageDao().insert(BluetoothMessage(message))
    }

    fun loadMessages(): LiveData<List<BluetoothMessage>> = db.bluetoothMessageDao().selectAll()

}