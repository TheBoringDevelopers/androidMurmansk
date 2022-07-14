package com.theboringdevelopers.smartmurmansk.data.model

/**
 * Серверная ошибка
 */
class ServerException(val code: Int? = null,
                      val errorInfo: ErrorInfo? = null): Exception("$code : ${errorInfo?.message}")