package com.theboringdevelopers.smartmurmansk.util.helpers

import com.theboringdevelopers.smartmurmansk.data.model.ErrorInfo
import com.theboringdevelopers.smartmurmansk.data.model.HttpResult
import com.theboringdevelopers.smartmurmansk.util.toErrorInfo
import retrofit2.Response

/**
 * Утилитные методы
 */
object HttpUtils {

    suspend fun <T: Any> safeApiCall(call: suspend () -> Response<T>) : HttpResult<T> = try {
        val response = call.invoke()
        if (response.isSuccessful) {
            HttpResult(_data = response.body(), code = 200)
        } else {
            val errorBody = response.errorBody()
            if (errorBody != null) {
                HttpResult(errorInfo = response.errorBody()!!.toErrorInfo(), code = response.code())
            } else {
                HttpResult(errorInfo = ErrorInfo(error = "Внутренняя ошибка сервера"), code = response.code())
            }
        }
    } catch (e: Throwable) {
        HttpResult(exception = e)
    }
}