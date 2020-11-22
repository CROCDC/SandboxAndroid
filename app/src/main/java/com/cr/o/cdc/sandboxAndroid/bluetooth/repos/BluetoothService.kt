package com.cr.o.cdc.sandboxAndroid.bluetooth.repos

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.os.Bundle
import android.os.Handler
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.UUID

/**
 * Created by Cami on 11/21/20.
 */


class BluetoothService(val handler: Handler) {

    private lateinit var connectThread: ConnectThread

    private lateinit var connectedThread: ConnectedThread

    private inner class ConnectThread(
        device: BluetoothDevice,
        val bluetoothAdapter: BluetoothAdapter?
    ) : Thread() {

        private val mmSocket: BluetoothSocket? by lazy(LazyThreadSafetyMode.NONE) {
            device.createRfcommSocketToServiceRecord(
                UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
            )
        }

        override fun run() {
            bluetoothAdapter?.cancelDiscovery()

            mmSocket?.use { socket ->
                socket.connect()
                connectedThread = ConnectedThread(socket)
            }
        }

        fun cancel() {
            try {
                mmSocket?.close()
            } catch (e: IOException) {
            }
        }
    }

    private inner class ConnectedThread(private val mmSocket: BluetoothSocket) : Thread() {

        private val mmInStream: InputStream = mmSocket.inputStream
        private val mmOutStream: OutputStream = mmSocket.outputStream
        private val mmBuffer: ByteArray = ByteArray(1024) // mmBuffer store for the stream

        override fun run() {
            var numBytes: Int // bytes returned from read()

            while (true) {
                numBytes = try {
                    mmInStream.read(mmBuffer)
                } catch (e: IOException) {
                    break
                }

                val readMsg = handler.obtainMessage(
                    MESSAGE_READ, numBytes, -1,
                    mmBuffer
                )
                readMsg.sendToTarget()
            }
        }

        fun write(bytes: ByteArray) {
            try {
                mmOutStream.write(bytes)
            } catch (e: IOException) {

                val writeErrorMsg = handler.obtainMessage(MESSAGE_TOAST)
                val bundle = Bundle().apply {
                    putString("toast", "Couldn't send data to the other device")
                }
                writeErrorMsg.data = bundle
                handler.sendMessage(writeErrorMsg)
                return
            }

            val writtenMsg = handler.obtainMessage(
                MESSAGE_WRITE, -1, -1, mmBuffer
            )
            writtenMsg.sendToTarget()
        }

        fun cancel() {
            try {
                mmSocket.close()
            } catch (e: IOException) {
            }
        }
    }

    fun connect(device: BluetoothDevice, manager: BluetoothManager) {
        //!manager.getConnectedDevices(GATT).contains(device)
        if (!::connectThread.isInitialized) {
            connectThread = ConnectThread(device, manager.adapter)
            connectThread.run()
        }
    }

    fun sendMessage(message: String) {
        if (::connectedThread.isInitialized) {
            connectedThread.write(message.toByteArray())
        }
    }

    companion object {
        private const val MESSAGE_READ: Int = 0
        private const val MESSAGE_WRITE: Int = 1
        private const val MESSAGE_TOAST: Int = 2
    }
}