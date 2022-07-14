package com.theboringdevelopers.smartmurmansk.util

/**
 * Базовый класс событий из ViewModel
 */
open class Event<out T>(private val content: T) {

    /**
     * Флаг того, что событие уже было обработано
     */
    @Suppress("MemberVisibilityCanBePrivate")
    var hasBeenHandled = false
        private set

    /**
     * Возвращает содержимое и запрещает его повторное использование
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Возвращает содержимое всегда
     */
    fun peekContent(): T = content
}