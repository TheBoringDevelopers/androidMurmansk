package com.theboringdevelopers.smartmurmansk.data.repository

import android.content.Context
import com.theboringdevelopers.smartmurmansk.R
import com.theboringdevelopers.smartmurmansk.context.UserContext
import com.theboringdevelopers.smartmurmansk.data.model.ServerException
import com.theboringdevelopers.smartmurmansk.data.model.bean.UserInfo
import com.theboringdevelopers.smartmurmansk.data.model.request.RegistrationConfirmationRequest
import com.theboringdevelopers.smartmurmansk.data.model.response.RegistrationResponse
import com.theboringdevelopers.smartmurmansk.data.remote.ServerApi
import com.theboringdevelopers.smartmurmansk.util.helpers.HttpUtils.safeApiCall
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.ResponseBody
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val serverApi: ServerApi,
    @ApplicationContext private val context: Context) {

    suspend fun login(
        username: String,
        password: String,
        userContext: UserContext,
        error: (String) -> Unit) : LoginResponse {
        try {
            // Логинимся
            val authInfo = safeApiCall {
                serverApi.login(
                    username,
                    password,
                    "backend-rest-client",
                    "backend-rest-client",
                    "password"
                ).await()
            }.data

            // Устанавливаем контекст
            userContext.userId = authInfo.userId
            userContext.login = username
            userContext.accessToken = authInfo.accessToken
            userContext.refreshToken = authInfo.refreshToken

            val user = safeApiCall {
                serverApi.user().await()
            }.data

            userContext.name = user.firstName
            userContext.lastName = user.lastName
            userContext.gender = user.gender
            userContext.age = user.age
            userContext.patronymic = user.patronymic

            return LoginResponse(
                null,
                true,
                userContext
            )
        } catch (se: ServerException) {
            if (se.code == 400) {
                val registrationResponse = registration(username, error)
                if (registrationResponse != null) {
                    return LoginResponse(
                        registrationResponse,
                        true,
                        userContext
                    )
                }
            } else {
                error(se.message.toString())
            }
        } catch (e: Throwable) {
            error(e.message.toString())
        }
        return LoginResponse(
            null,
            false,
            userContext
        )
    }

    suspend fun registration(
        username: String,
        error: (String) -> Unit) : RegistrationResponse? {
        try {
            val regInfo = safeApiCall {
                serverApi.registration(
                    username,
                ).await()
            }.data
            return regInfo
        } catch (se: ServerException) {
            if (se.code == 400) {
                error(context.getString(R.string.request_error))
            } else {
                error(se.message.toString())
            }
        } catch (e: Throwable) {
            error(e.message.toString())
        }
        return null
    }

    suspend fun registrationConfirm(
        phoneNumber: String,
        confirmationCode: String,
        sessionId: String
    ): ResponseBody {
        return safeApiCall {
            serverApi.registrationConfirm(RegistrationConfirmationRequest(
                UserInfo(
                       phoneNumber,
                    "123456",
                    "",
                    "",
                    "",
                    0,
                    true,
                    ""
                ),
                confirmationCode,
                sessionId,
                emptyList()
            )).await()
        }.data
    }

    data class LoginResponse(
        val registrationResponse: RegistrationResponse?,
        val success: Boolean,
        val userContext: UserContext
    )
}


