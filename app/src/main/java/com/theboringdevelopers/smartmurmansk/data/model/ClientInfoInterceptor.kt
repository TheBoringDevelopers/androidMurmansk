package com.theboringdevelopers.smartmurmansk.data.model

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Внутренний класс для добавления информации о клиенте в хидер
 */
class ClientInfoInterceptor(
    /** ИД приложения  */
    private val applicationId: String,
    /** Версия приложения  */
    private val appVersion: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        builder.header("X-Client-Version", appVersion)
        builder.header("X-Client-Os", "Android")
        builder.header("X-Client-Name", applicationId)
        return chain.proceed(builder.build())
    }
}