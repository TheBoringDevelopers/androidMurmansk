package com.theboringdevelopers.smartmurmansk.data.model.request

import com.theboringdevelopers.smartmurmansk.data.model.bean.SportType
import com.theboringdevelopers.smartmurmansk.data.model.bean.UserInfo

data class RegistrationConfirmationRequest (
    val userInfo: UserInfo,
    val confirmationCode: String,
    val sessionId: String,
    val sportTypeList: List<SportType>
)