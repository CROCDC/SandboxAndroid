package com.cr.o.cdc.sandboxAndroid.bluetooth.repos

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.UUID

/**
 * Created by Cami on 11/21/20.
 */

class BluetoothService {
    private lateinit var connectThread: ConnectThread

    private lateinit var connectedThread: ConnectedThread

    private lateinit var bluetoothDevice: BluetoothDevice

    private var pendingMessage: String? = null

    private inner class ConnectThread(private val adapter: BluetoothAdapter) : Thread() {

        private val bluetoothSocket: BluetoothSocket? by lazy(LazyThreadSafetyMode.NONE) {
            bluetoothDevice.createRfcommSocketToServiceRecord(
                bluetoothDevice.takeIf { it.uuids != null }?.uuids?.getOrNull(0)?.uuid
                    ?: UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
            )
        }

        override fun run() {
            adapter.cancelDiscovery()

            try {
                bluetoothSocket?.use { socket ->
                    socket.connect()
                    connectedThread = ConnectedThread(socket)
                    connectedThread.run()
                    pendingMessage?.let {
                        connectedThread.write(it)
                    }
                }
            } catch (e: IOException) {
                Log.e("Cami", e.toString())
            }

        }

        fun cancel() {
            try {
                bluetoothSocket?.close()
            } catch (e: IOException) {
            }
        }
    }

    private inner class ConnectedThread(
        private val mmSocket: BluetoothSocket
    ) : Thread() {
        private val mmInStream: InputStream?
        private val mmOutStream: OutputStream?
        override fun run() {
        }

        /* Call this from the main activity to send data to the remote device */
        fun write(input: String) {
            val bytes = input.toByteArray() //converts entered String into bytes
            try {
                mmOutStream!!.write(bytes)
            } catch (e: IOException) {
                //todo handle
            }
        }

        /* Call this from the main activity to shutdown the connection */
        fun cancel() {
            try {
                mmSocket.close()
            } catch (e: IOException) {
            }
        }

        init {
            var tmpIn: InputStream? = null
            var tmpOut: OutputStream? = null

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = mmSocket.inputStream
                tmpOut = mmSocket.outputStream
            } catch (e: IOException) {
                Log.e("Cami", e.toString())
            }
            mmInStream = tmpIn
            mmOutStream = tmpOut
        }

        fun isConnected() = mmSocket.isConnected
    }

    fun connect(device: BluetoothDevice, adapter: BluetoothAdapter) {
        this.bluetoothDevice = device
        connectThread = ConnectThread(adapter)
        connectThread.start()
    }

    fun sendMessage(message: String, macAddress: String, adapter: BluetoothAdapter) {
        if (::connectedThread.isInitialized && connectedThread.isConnected()) {
            connectedThread.write(message)
        } else {
            val device = adapter.bondedDevices?.find { it.address == macAddress }
            if (device != null) {
                this.bluetoothDevice = device
                pendingMessage = message
                connectThread = ConnectThread(adapter)
                connectThread.start()
            } else {
                //todo return device not connected
            }
        }
    }

}