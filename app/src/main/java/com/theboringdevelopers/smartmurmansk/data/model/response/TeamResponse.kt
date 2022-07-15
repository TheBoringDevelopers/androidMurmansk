package com.theboringdevelopers.smartmurmansk.data.model.response

import com.theboringdevelopers.smartmurmansk.data.model.bean.SportType
import com.theboringdevelopers.smartmurmansk.data.model.bean.Team

data class TeamResponse(
    val team: Team,
    val users: List<UserResponse>,
    val sportTypes: List<SportType>
)
