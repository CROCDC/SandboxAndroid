package com.cr.o.cdc.sandboxAndroid.whatsapputils.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cr.o.cdc.sandboxAndroid.databinding.ListItemWhatsappMessageBotBinding
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.model.WhatsappMessageBot

class WhatsappMessagesBotAdapter :
    ListAdapter<WhatsappMessageBot, WhatsappMessagesBotAdapter.ViewHolder>(CallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ListItemWhatsappMessageBotBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ViewHolder(private val binding: ListItemWhatsappMessageBotBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(whatsappMessageBot: WhatsappMessageBot) {
            binding.txtContact.text = whatsappMessageBot.contact
            binding.txtMessage.text = whatsappMessageBot.message
            binding.txtResponse.text = whatsappMessageBot.response
        }
    }

    object CallBack : DiffUtil.ItemCallback<WhatsappMessageBot>() {
        override fun areItemsTheSame(
            oldItem: WhatsappMessageBot,
            newItem: WhatsappMessageBot
        ): Boolean =
            oldItem.message == newItem.message

        override fun areContentsTheSame(
            oldItem: WhatsappMessageBot,
            newItem: WhatsappMessageBot
        ): Boolean = oldItem == newItem

    }
}