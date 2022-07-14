package com.theboringdevelopers.smartmurmansk.util.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Биндинги
 */
object DataBindingAdapters {

    @JvmStatic
    @BindingAdapter("changeVisibility")
    fun changeVisibility(view: View, value: Boolean) {
        view.visibility = if (value) View.VISIBLE else View.GONE
    }

    /**
     * Биндинг для списочных моделей
     */
    @JvmStatic
    @BindingAdapter("itemViewModels", "onItemClick", "onItemLongClick", requireAll = false)
    fun bindItemViewModelsWithListener(recyclerView: RecyclerView,
                                       itemViewModels: List<ItemViewModel>?,
                                       listener: OnItemClickListener?,
                                       longListener: OnItemLongClickListener?) {
        val adapter = getOrCreateAdapter(recyclerView, listener, longListener)
        adapter.updateItems(itemViewModels)
    }

    /**
     * Биндинг картинки по ресурсу
     */
    @JvmStatic
    @BindingAdapter("srcDrawable")
    fun bindItemViewModels(imageView: ImageView,
                           src: Int) {
        imageView.setImageDrawable(imageView.context.resources.getDrawable(src))
    }


    @JvmStatic
    private fun getOrCreateAdapter(recyclerView: RecyclerView,
                                   listener: OnItemClickListener? = null,
                                   longListener: OnItemLongClickListener? = null): BindableRecyclerViewAdapter {
        return if (recyclerView.adapter != null && recyclerView.adapter is BindableRecyclerViewAdapter) {
            recyclerView.adapter as BindableRecyclerViewAdapter
        } else {
            val bindableRecyclerAdapter = BindableRecyclerViewAdapter(listener, longListener)
            recyclerView.adapter = bindableRecyclerAdapter
            bindableRecyclerAdapter
        }
    }
}