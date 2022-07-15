package com.theboringdevelopers.smartmurmansk.data.model.request

import com.theboringdevelopers.smartmurmansk.data.model.bean.SportType
import com.theboringdevelopers.smartmurmansk.data.model.bean.Team

data class CreateTeamRequest(
    val userId: Long,
    val team: Team,
    val sportTypes: List<SportType>
)