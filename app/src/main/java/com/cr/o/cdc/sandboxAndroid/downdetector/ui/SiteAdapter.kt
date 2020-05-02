package com.cr.o.cdc.sandboxAndroid.downdetector.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.databinding.ListItemSiteBinding
import com.cr.o.cdc.sandboxAndroid.downdetector.db.model.Site

class SiteAdapter(val listener: SiteAdapterListener) :
    ListAdapter<Site, SiteAdapter.ViewHolder>(CallBack) {

    interface SiteAdapterListener {
        fun modifyEnable(address: String, enable: Boolean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ListItemSiteBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        listener
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ListItemSiteBinding,
        private val listener: SiteAdapterListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(site: Site) {
            binding.txtName.text = site.name
            binding.txtAddress.text = site.address
            binding.checkbox.setImageResource(
                if (site.enable) {
                    R.drawable.ic_check_black_24dp
                } else {
                    R.drawable.ic_crop_square_black_24dp
                }
            )

            binding.checkbox.setOnClickListener {
                listener.modifyEnable(site.address, !site.enable)
            }
        }

    }

    object CallBack : DiffUtil.ItemCallback<Site>() {
        override fun areItemsTheSame(oldItem: Site, newItem: Site): Boolean =
            oldItem.address == newItem.address

        override fun areContentsTheSame(oldItem: Site, newItem: Site): Boolean = oldItem == newItem

    }
}
