package com.theboringdevelopers.smartmurmansk.data.model

/**
 * Результат выполнения запроса
 */
class HttpResult<out T: Any>(private val _data: T? = null,
                             val code: Int? = null,
                             val errorInfo: ErrorInfo? = null,
                             val exception: Throwable? = null) {

    /**
     * Возврат данных с выкидывание ошибки
     */
    val data: T
        get() = _data
            ?: if (exception != null) {
                throw exception
            } else {
                throw ServerException(code, errorInfo)
            }

    /**
     * Проверка запроса на ошибки
     */
    fun checkError() =
        if (exception != null)
            throw exception
        else {
            if (code != 200)
                throw ServerException(code, errorInfo)
            else
                Unit
        }
}