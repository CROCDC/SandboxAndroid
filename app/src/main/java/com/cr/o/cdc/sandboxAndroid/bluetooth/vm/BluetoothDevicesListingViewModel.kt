package com.cr.o.cdc.sandboxAndroid.bluetooth.vm

import android.bluetooth.BluetoothDevice
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.bluetooth.db.model.BluetoothMessage
import com.cr.o.cdc.sandboxAndroid.bluetooth.model.BluetoothConnection
import com.cr.o.cdc.sandboxAndroid.bluetooth.model.BluetoothScan
import com.cr.o.cdc.sandboxAndroid.bluetooth.repos.BluetoothRepository

class BluetoothDevicesListingViewModel @ViewModelInject constructor(private val repository: BluetoothRepository) :
    ViewModel() {

    fun startScan(): LiveData<BluetoothScan> = repository.startScan()

    val bluetoothMessages: LiveData<List<BluetoothMessage>> = repository.loadMessages()

    fun connect(device: BluetoothDevice): LiveData<BluetoothConnection> =repository.connect(device)

    fun sendMessage(message: String) {
        repository.sendMessage(message)
    }

    fun addBluetoothMessage(message: String) {
        repository.addBluetoothMessage(message)
    }
}