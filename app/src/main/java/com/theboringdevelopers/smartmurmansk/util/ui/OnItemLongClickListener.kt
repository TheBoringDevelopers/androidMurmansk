package com.theboringdevelopers.smartmurmansk.util.ui

import android.view.View

/**
 * Слушатель долгого нажатия на список
 */
interface OnItemLongClickListener {

    /**
     * Коллбэк нажатия
     * @param view нажатое view
     * @param item нажатый элемент
     */
    fun onItemClick(view: View, item: ItemViewModel): Boolean
}