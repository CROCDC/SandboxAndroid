package com.cr.o.cdc.sandboxAndroid.bitbucket.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.bitbucket.model.Workspace

class WorkspacesAdapter : ListAdapter<Workspace, WorkspacesAdapter.ViewHolder>(Callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_workspace, parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            bind(getItem(position))
            view.setOnClickListener {

            }
        }

    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val txtName = view.findViewById<TextView>(R.id.txt_name)

        fun bind(workspace: Workspace) {
            txtName.text = workspace.name
        }
    }

    object Callback : DiffUtil.ItemCallback<Workspace>() {
        override fun areItemsTheSame(oldItem: Workspace, newItem: Workspace) =
            oldItem.name == oldItem.name

        override fun areContentsTheSame(oldItem: Workspace, newItem: Workspace): Boolean =
            oldItem == newItem
    }
}
