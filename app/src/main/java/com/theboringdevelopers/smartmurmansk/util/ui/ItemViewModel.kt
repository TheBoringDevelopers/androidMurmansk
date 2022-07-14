package com.theboringdevelopers.smartmurmansk.util.ui

import androidx.annotation.LayoutRes


/**
 * Базовый интерфейс для списочных данных
 */
interface ItemViewModel {

    /** Ид ресурса разметки */
    @get:LayoutRes
    val layoutId: Int

    /** Тип ресурса (если в списке разные типы -
     * должны возвращаться разные значения)
     */
    val viewType: Int
        get() = 0
}