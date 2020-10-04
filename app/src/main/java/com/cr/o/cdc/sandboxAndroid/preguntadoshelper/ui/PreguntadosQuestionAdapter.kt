package com.cr.o.cdc.sandboxAndroid.preguntadoshelper.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cr.o.cdc.sandboxAndroid.databinding.ListItemPreguntadosQuestionBinding
import com.cr.o.cdc.sandboxAndroid.preguntadoshelper.db.model.PreguntadosQuestion

class PreguntadosQuestionAdapter :
    ListAdapter<PreguntadosQuestion, PreguntadosQuestionAdapter.ViewHolder>(
        PreguntadosQuestionDiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ListItemPreguntadosQuestionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ListItemPreguntadosQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(preguntadosQuestion: PreguntadosQuestion) {
            binding.txtQuestionTitle.text = preguntadosQuestion.questionTitle
            binding.txtAnswer.text = preguntadosQuestion.answer
        }

    }

}

object PreguntadosQuestionDiffCallback : DiffUtil.ItemCallback<PreguntadosQuestion>() {

    override fun areItemsTheSame(
        oldItem: PreguntadosQuestion,
        newItem: PreguntadosQuestion
    ): Boolean = oldItem.questionTitle == newItem.questionTitle

    override fun areContentsTheSame(
        oldItem: PreguntadosQuestion,
        newItem: PreguntadosQuestion
    ): Boolean = oldItem == newItem

}
