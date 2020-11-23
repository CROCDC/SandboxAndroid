package com.cr.o.cdc.sandboxAndroid.bluetooth.vm

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.bluetooth.db.model.BluetoothMessage
import com.cr.o.cdc.sandboxAndroid.bluetooth.repos.BluetoothRepository

class BluetoothDevicesListingViewModel @ViewModelInject constructor(private val repository: BluetoothRepository) :
    ViewModel() {

    private val _devices: MutableLiveData<Set<BluetoothDevice>> = MutableLiveData()

    val devices: LiveData<Set<BluetoothDevice>>
        get() = _devices

    val bluetoothMessages: LiveData<List<BluetoothMessage>> = repository.loadMessages()

    fun addDevice(bluetoothDevice: BluetoothDevice) {
        _devices.value = (_devices.value ?: setOf()).plus(bluetoothDevice)
    }

    fun connect(device: BluetoothDevice, adapter: BluetoothAdapter) {
        repository.connect(device, adapter)
    }

    fun sendMessage(message: String, macAddress: String, adapter: BluetoothAdapter) {
        repository.sendMessage(message, macAddress, adapter)
    }

    fun addBluetoothMessage(message: String) {
        repository.addBluetoothMessage(message)
    }
}