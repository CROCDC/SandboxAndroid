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


class BluetoothService(
    val bluetoothAdapter: BluetoothAdapter
) {
    private lateinit var connectThread: ConnectThread

    private lateinit var connectedThread: ConnectedThread

    private lateinit var bluetoothDevice: BluetoothDevice

    private var pendingMessage: String? = null

    private inner class ConnectThread(device: BluetoothDevice) : Thread() {

        private val bluetoothSocket: BluetoothSocket? by lazy(LazyThreadSafetyMode.NONE) {
            device.createRfcommSocketToServiceRecord(
                device.takeIf { it.uuids != null }?.uuids?.getOrNull(0)?.uuid
                    ?: UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
            )
        }

        override fun run() {
            bluetoothAdapter.cancelDiscovery()

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

            }

        }

        fun cancel() {
            try {
                bluetoothSocket?.close()
            } catch (e: IOException) {
            }
        }
    }

    private inner class ConnectedThread(private val mmSocket: BluetoothSocket) : Thread() {
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
                Log.e("Send Error", "Unable to send message", e)
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
            }
            mmInStream = tmpIn
            mmOutStream = tmpOut
        }
    }

    fun connect(device: BluetoothDevice) {
        connectThread = ConnectThread(device)
        connectThread.start()
        this.bluetoothDevice = device

    }

    fun sendMessage(message: String, macAddress: String) {
        if (::connectedThread.isInitialized) {
            connectedThread.write(message)
        } else {
            val device = bluetoothAdapter.bondedDevices?.find { it.address == macAddress }
            if (device != null) {
                pendingMessage = message
                ConnectThread(device).start()
            } else {
                //todo return device not connected
            }
        }
    }
}