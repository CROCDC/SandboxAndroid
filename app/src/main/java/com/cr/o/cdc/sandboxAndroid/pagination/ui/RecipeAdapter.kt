package com.cr.o.cdc.sandboxAndroid.pagination.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.pagination.db.model.PagedRecipe
import com.cr.o.cdc.sandboxAndroid.pagination.db.model.Recipe

class RecipeAdapter : PagedListAdapter<PagedRecipe, RecipeAdapter.ViewHolder>(RecipeDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_recipe, parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it.recipe) }
    }


    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val txt = view.findViewById<TextView>(R.id.txt)

        fun bind(recipe: Recipe) {
            txt.text = recipe.label
        }
    }

    object RecipeDiffCallback : DiffUtil.ItemCallback<PagedRecipe>() {
        override fun areItemsTheSame(oldItem: PagedRecipe, newItem: PagedRecipe): Boolean =
            oldItem.recipe.uri == newItem.recipe.uri

        override fun areContentsTheSame(oldItem: PagedRecipe, newItem: PagedRecipe): Boolean =
            oldItem == newItem

    }
}