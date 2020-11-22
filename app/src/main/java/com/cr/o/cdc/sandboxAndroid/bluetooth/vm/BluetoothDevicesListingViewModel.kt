package com.cr.o.cdc.sandboxAndroid.bluetooth.vm

import android.bluetooth.BluetoothDevice
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BluetoothDevicesListingViewModel : ViewModel() {

    private val _devices: MutableLiveData<Set<BluetoothDevice>> = MutableLiveData()

    val devices: LiveData<Set<BluetoothDevice>>
        get() = _devices

    fun addDevice(bluetoothDevice: BluetoothDevice) {
        _devices.value = (_devices.value ?: setOf()).plus(bluetoothDevice)
    }
}