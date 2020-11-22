package com.cr.o.cdc.sandboxAndroid.bluetooth.ui.adapters

import android.bluetooth.BluetoothDevice
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cr.o.cdc.sandboxAndroid.databinding.ListItemBluetoothDeviceBinding

/**
 * Created by Cami on 11/21/20.
 */
class BluetoothDeviceAdapter(private val listener: BluetoothDeviceAdapterListener) :
    ListAdapter<BluetoothDevice, BluetoothDeviceAdapter.ViewHolder>(DiffCallback) {

    interface BluetoothDeviceAdapterListener {
        fun connect(bluetoothDevice: BluetoothDevice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        listener,
        ListItemBluetoothDeviceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val listener: BluetoothDeviceAdapterListener,
        private val binding: ListItemBluetoothDeviceBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(bluetoothDevice: BluetoothDevice) {
            val name = bluetoothDevice.name
            binding.txtDeviceName.isVisible = name != null
            binding.txtDeviceName.text = name
            binding.txtMacAdress.text = bluetoothDevice.address

            binding.btnConnect.setOnClickListener {
                listener.connect(bluetoothDevice)
            }
        }
    }


    object DiffCallback : DiffUtil.ItemCallback<BluetoothDevice>() {
        override fun areItemsTheSame(oldItem: BluetoothDevice, newItem: BluetoothDevice): Boolean =
            oldItem.address == newItem.address

        override fun areContentsTheSame(
            oldItem: BluetoothDevice,
            newItem: BluetoothDevice
        ): Boolean = oldItem == newItem
    }

}

