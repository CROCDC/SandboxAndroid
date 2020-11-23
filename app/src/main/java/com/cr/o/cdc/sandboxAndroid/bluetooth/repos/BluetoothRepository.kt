package com.cr.o.cdc.sandboxAndroid.bluetooth.repos

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import androidx.lifecycle.LiveData
import com.cr.o.cdc.sandboxAndroid.bluetooth.db.model.BluetoothMessage
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.SandBoxDB
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Cami on 11/22/20.
 */
@Singleton
class BluetoothRepository @Inject constructor(
    private val db: SandBoxDB
) {

    private val bluetoothService: BluetoothService = BluetoothService()

    fun connect(device: BluetoothDevice, adapter: BluetoothAdapter) {
        bluetoothService.connect(device, adapter)
    }

    fun sendMessage(message: String, macAddress: String, adapter: BluetoothAdapter) {
        bluetoothService.sendMessage(message, macAddress, adapter)
    }

    fun addBluetoothMessage(message: String) {
        db.bluetoothMessageDao().insert(BluetoothMessage(message))
    }

    fun loadMessages(): LiveData<List<BluetoothMessage>> = db.bluetoothMessageDao().selectAll()

}