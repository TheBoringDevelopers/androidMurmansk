package com.theboringdevelopers.smartmurmansk.data.repository

import com.theboringdevelopers.smartmurmansk.context.UserContext
import com.theboringdevelopers.smartmurmansk.data.model.bean.SportType
import com.theboringdevelopers.smartmurmansk.data.model.bean.UserInfo
import com.theboringdevelopers.smartmurmansk.data.model.request.UpdateUserRequest
import com.theboringdevelopers.smartmurmansk.data.model.response.UserResponse
import com.theboringdevelopers.smartmurmansk.data.remote.ServerApi
import com.theboringdevelopers.smartmurmansk.util.helpers.HttpUtils
import okhttp3.ResponseBody
import javax.inject.Inject

class UserRepository@Inject constructor(
    private val serverApi: ServerApi,
    val userContext: UserContext
) {

    suspend fun updateUserInfo(
        phoneNumber: String,
        firstName: String,
        lastName: String,
        patronymic: String,
        age: Long,
        gender: Boolean,
        sportTypes: List<SportType>,
    ): ResponseBody {
        return HttpUtils.safeApiCall {
            serverApi.updateUser(UpdateUserRequest(UserInfo(
                phoneNumber = phoneNumber,
                firstName = firstName,
                lastName = lastName,
                patronymic = patronymic,
                age = age,
                gender = gender
            ), sportTypes)).await()
        }.data
    }

    suspend fun getUser(id: Long) : UserResponse {
        return try {
            val result = HttpUtils.safeApiCall {
                serverApi.userById(id).await()
            }.data
            result
        } catch (th: Throwable) {
            error(th.message.toString())
        }
    }
}