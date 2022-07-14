package com.theboringdevelopers.smartmurmansk.data.model

import com.google.gson.annotations.SerializedName

/**
 * Бин ответа с ошибкой
 */
class ErrorInfo (
    val error: String,
    @SerializedName("error_description")
    val errorDescription: String? = null,
    val message: String? = null

)