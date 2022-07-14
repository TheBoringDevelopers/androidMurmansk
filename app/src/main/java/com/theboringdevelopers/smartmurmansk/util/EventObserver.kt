package com.theboringdevelopers.smartmurmansk.util

import androidx.lifecycle.Observer

/**
 * Кастомный слушатель событий для обработки
 * Не возвращает событие, если оно уже было обработано
 */
class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {

    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}