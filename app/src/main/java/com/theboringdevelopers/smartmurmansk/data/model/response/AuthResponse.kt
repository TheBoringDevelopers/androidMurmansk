package com.theboringdevelopers.smartmurmansk.data.model.response

import com.google.gson.annotations.SerializedName

/**
 * Бин авторизации
 */
class AuthResponse (

    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("expires_in")
    val expiresIn: Long,
    val scope: String,
    @SerializedName("user_id")
    val userId: Long

)