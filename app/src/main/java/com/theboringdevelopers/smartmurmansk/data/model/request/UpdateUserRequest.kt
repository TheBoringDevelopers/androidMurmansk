package com.theboringdevelopers.smartmurmansk.data.model.request

import com.theboringdevelopers.smartmurmansk.data.model.bean.SportType
import com.theboringdevelopers.smartmurmansk.data.model.bean.UserInfo

data class UpdateUserRequest(
    val user: UserInfo,
    val sportTypeList: List<SportType>
)