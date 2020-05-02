package com.cr.o.cdc.sandboxAndroid.coronavirus.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.coronavirus.db.model.CountryStat
import com.cr.o.cdc.sandboxAndroid.databinding.ListItemCountryStatBinding

class CountryStatsAdapter : ListAdapter<CountryStat, CountryStatsAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ListItemCountryStatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ListItemCountryStatBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(countryStat: CountryStat) {
            binding.txtCountyName.text = countryStat.country_name
            binding.txtQuantityCases.text =
                itemView.context.getString(R.string.cases, countryStat.cases)
            binding.txtQuantityDeaths.text =
                itemView.context.getString(R.string.deaths, countryStat.deaths)
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<CountryStat>() {
        override fun areItemsTheSame(oldItem: CountryStat, newItem: CountryStat): Boolean =
            oldItem.country_name == newItem.country_name

        override fun areContentsTheSame(oldItem: CountryStat, newItem: CountryStat): Boolean =
            oldItem == newItem

    }

}