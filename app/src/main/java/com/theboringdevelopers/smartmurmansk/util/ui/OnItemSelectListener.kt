package com.theboringdevelopers.smartmurmansk.util.ui

/**
 * Слушатель выбора из списка
 */
interface OnItemSelectListener<T> {

    fun onSelect(item: T?)
}