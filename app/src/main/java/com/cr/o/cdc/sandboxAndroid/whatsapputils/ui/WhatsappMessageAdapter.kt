package com.cr.o.cdc.sandboxAndroid.whatsapputils.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cr.o.cdc.sandboxAndroid.databinding.ListItemWhatsappMessageBinding
import com.cr.o.cdc.sandboxAndroid.whatsapputils.model.WhatsappMessage

class WhatsappMessageAdapter :
    ListAdapter<WhatsappMessage, WhatsappMessageAdapter.ViewHolder>(CallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ListItemWhatsappMessageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ViewHolder(private val binding: ListItemWhatsappMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(whatsappMessage: WhatsappMessage) {
            binding.txtContact.text = whatsappMessage.contact
            binding.txtMessage.text = whatsappMessage.message
        }
    }

    object CallBack : DiffUtil.ItemCallback<WhatsappMessage>() {
        override fun areItemsTheSame(oldItem: WhatsappMessage, newItem: WhatsappMessage): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: WhatsappMessage,
            newItem: WhatsappMessage
        ): Boolean = oldItem == newItem

    }
}