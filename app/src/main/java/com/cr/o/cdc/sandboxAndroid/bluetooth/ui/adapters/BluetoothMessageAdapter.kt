package com.cr.o.cdc.sandboxAndroid.bluetooth.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cr.o.cdc.sandboxAndroid.bluetooth.db.model.BluetoothMessage
import com.cr.o.cdc.sandboxAndroid.databinding.ListItemBluetoothMessageBinding

/**
 * Created by Cami on 11/22/20.
 */
class BluetoothMessageAdapter(val listener: BluetoothMessageAdapterListener) :
    ListAdapter<BluetoothMessage, BluetoothMessageAdapter.ViewHolder>(DiffCallback) {

    interface BluetoothMessageAdapterListener {
        fun select(bluetoothMessage: BluetoothMessage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ListItemBluetoothMessageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        listener
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        val binding: ListItemBluetoothMessageBinding,
        val listener: BluetoothMessageAdapterListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(bluetoothMessage: BluetoothMessage) {
            binding.txtMessage.text = bluetoothMessage.message
            binding.txtMessage.setOnClickListener {
                listener.select(bluetoothMessage)
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<BluetoothMessage>() {
        override fun areItemsTheSame(
            oldItem: BluetoothMessage,
            newItem: BluetoothMessage
        ): Boolean = oldItem.message == newItem.message

        override fun areContentsTheSame(
            oldItem: BluetoothMessage,
            newItem: BluetoothMessage
        ): Boolean = oldItem == newItem

    }
}