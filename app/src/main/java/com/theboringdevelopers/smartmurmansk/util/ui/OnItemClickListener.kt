package com.theboringdevelopers.smartmurmansk.util.ui

import android.view.View

/**
 * Слушатель нажатия на список
 */
interface OnItemClickListener {

    /**
     * Коллбэк нажатия
     * @param view нажатое view
     * @param item нажатый элемент
     */
    fun onItemClick(view: View, item: ItemViewModel)
}