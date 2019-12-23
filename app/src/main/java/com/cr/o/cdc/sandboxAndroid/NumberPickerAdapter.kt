package com.cr.o.cdc.sandboxAndroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class NumberPickerAdapter : ListAdapter<Int, NumberPickerAdapter.ViewHolder>(Callback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_number_picker,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val txt = view.findViewById<TextView>(R.id.txt)
        fun bind(int: Int) {

            txt.text = int.toString()
            if (adapterPosition == int) {
                ContextCompat.getColor(itemView.context, R.color.colorAccent)
            } else {
                ContextCompat.getColor(itemView.context, R.color.colorPrimary)
            }.let {
                txt.setBackgroundColor(it)
            }
        }
    }

    object Callback : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean = oldItem == newItem
    }
}