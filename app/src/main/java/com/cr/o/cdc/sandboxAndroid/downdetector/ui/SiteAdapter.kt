package com.cr.o.cdc.sandboxAndroid.downdetector.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.databinding.ListItemSiteBinding
import com.cr.o.cdc.sandboxAndroid.downdetector.db.model.Site

class SiteAdapter(val listener: SiteAdapterListener) :
    ListAdapter<Site, SiteAdapter.ViewHolder>(CallBack) {

    interface SiteAdapterListener {
        fun modifyEnable(site: Site)
        fun deleteSite(address: String)
        fun testSite(address: String)
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
            binding.imgCheckbox.setImageResource(
                if (site.enable) {
                    R.drawable.ic_check_black_24dp
                } else {
                    R.drawable.ic_crop_square_black_24dp
                }
            )

            binding.imgCheckbox.setOnClickListener {
                listener.modifyEnable(site)
            }

            binding.imgDelete.setOnClickListener {
                listener.deleteSite(site.address)
            }

            binding.txtErrorsQuantity.text =
                itemView.context.getString(R.string.errors_quantity, site.cantErrors.toString())

            binding.txtInterval.text =
                itemView.context.getString(
                    R.string.interval_of_check,
                    site.intervalCheck.toString()
                )

            binding.txtRetriesQuantity.visibility = if (site.numberOfRetriesOfError != null) {
                binding.txtRetriesQuantity.text = itemView.context.getString(
                    R.string.retries_quantity,
                    site.numberOfRetriesOfError.toString()
                )
                View.VISIBLE
            } else {
                View.GONE
            }

            binding.txtIsWorking.setText(
                when (site.isWorking) {
                    true -> R.string.good
                    false -> R.string.bad
                    null -> R.string.unknown
                }
            )

            binding.txtIsWorking.setBackgroundResource(
                when (site.isWorking) {
                    true -> R.color.green_00C803
                    false -> R.color.red_FF0000
                    null -> R.color.gray_A56C6C
                }
            )

            binding.txtIsWorking.setOnClickListener {
                site.lastErrorMessage?.let { error ->
                    Toast.makeText(it.context, error, Toast.LENGTH_LONG).show()
                }
            }

            binding.btnTest.setOnClickListener {
                listener.testSite(site.address)
            }
        }
    }

    object CallBack : DiffUtil.ItemCallback<Site>() {
        override fun areItemsTheSame(oldItem: Site, newItem: Site): Boolean =
            oldItem.address == newItem.address

        override fun areContentsTheSame(oldItem: Site, newItem: Site): Boolean = oldItem == newItem
    }
}
