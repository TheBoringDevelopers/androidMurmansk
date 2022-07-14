package com.theboringdevelopers.smartmurmansk.util.ui

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.theboringdevelopers.smartmurmansk.BR

/**
 * Контейнер элемента списка
 */
class BindableViewHolder(private val binding: ViewDataBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(itemViewModel: ItemViewModel, listener: OnItemClickListener?,
             longListener: OnItemLongClickListener?) {
        binding.setVariable(BR.itemViewModel, itemViewModel)
        if (listener != null) {
            itemView.setOnClickListener{ listener.onItemClick(itemView, itemViewModel) }
        }
        if (longListener != null) {
            itemView.setOnLongClickListener{
                longListener.onItemClick(itemView, itemViewModel)
            }
        }
    }

}