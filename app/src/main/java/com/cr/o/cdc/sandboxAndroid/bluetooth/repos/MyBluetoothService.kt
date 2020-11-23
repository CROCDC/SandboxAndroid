package com.cr.o.cdc.sandboxAndroid.bluetooth.repos

import android.bluetooth.BluetoothDevice
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cr.o.cdc.sandboxAndroid.bluetooth.model.BluetoothConnection
import com.cr.o.cdc.sandboxAndroid.bluetooth.model.BluetoothScan
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothClassicService
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothConfiguration
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothService
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothStatus
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothWriter
import java.util.UUID

/**
 * Created by Cami on 11/21/20.
 */

class MyBluetoothService(context: Context) {

    init {
        val config = BluetoothConfiguration()
        config.context = context
        config.bluetoothServiceClass = BluetoothClassicService::class.java
        config.bufferSize = 1024
        config.characterDelimiter = '\n'
        config.deviceName = "SandBox"
        config.callListenersInMainThread = false
        config.uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb")
        BluetoothService.init(config)
    }

    fun startScan(): LiveData<BluetoothScan> {
        val result = MutableLiveData<BluetoothScan>()
        val service = BluetoothService.getDefaultInstance()

        service.setOnScanCallback(object : BluetoothService.OnBluetoothScanCallback {
            override fun onDeviceDiscovered(device: BluetoothDevice, rssi: Int) {
                val list = (result.value?.devices ?: setOf()).plus(device)
                result.postValue(
                    result.value?.copy(devices = list) ?: BluetoothScan(
                        true,
                        list
                    )
                )
            }

            override fun onStartScan() {
                result.postValue(
                    result.value?.copy(isScanning = true) ?: BluetoothScan(
                        true,
                        setOf()
                    )
                )
            }

            override fun onStopScan() {
                result.postValue(
                    result.value?.copy(isScanning = false) ?: BluetoothScan(false, setOf())
                )
            }
        })
        service.startScan()
        return result
    }

    fun connect(bluetoothDevice: BluetoothDevice): LiveData<BluetoothConnection> {
        val result = MutableLiveData<BluetoothConnection>()
        val service = BluetoothService.getDefaultInstance()
        service.status
        service.stopScan()
        service.setOnEventCallback(object : BluetoothService.OnBluetoothEventCallback {
            override fun onDataRead(buffer: ByteArray, length: Int) {
            }

            override fun onStatusChange(status: BluetoothStatus) {
                result.postValue(
                    result.value?.copy(status = status) ?: BluetoothConnection(null, status, null)
                )
            }

            override fun onDeviceName(deviceName: String) {
                result.postValue(
                    result.value?.copy(deviceName = deviceName) ?: BluetoothConnection(
                        null,
                        null,
                        deviceName
                    )
                )
            }

            override fun onToast(message: String) {
                result.postValue(
                    result.value?.copy(message = message) ?: BluetoothConnection(
                        message,
                        null,
                        null
                    )
                )
            }

            override fun onDataWrite(buffer: ByteArray) {
            }
        })
        service.connect(bluetoothDevice)

        return result
    }

    fun write(message: String) {
        BluetoothWriter(BluetoothService.getDefaultInstance()).writeln(message)
    }
}
