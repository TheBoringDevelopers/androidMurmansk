package com.theboringdevelopers.smartmurmansk.data.repository

import com.theboringdevelopers.smartmurmansk.context.UserContext
import com.theboringdevelopers.smartmurmansk.data.model.bean.UserInfo
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
        gender: Boolean
    ): ResponseBody {
        return HttpUtils.safeApiCall {
            serverApi.updateUser(UserInfo(
                phoneNumber = phoneNumber,
                firstName = firstName,
                lastName = lastName,
                patronymic = patronymic,
                age = age,
                gender = gender
            )).await()
        }.data
    }
}