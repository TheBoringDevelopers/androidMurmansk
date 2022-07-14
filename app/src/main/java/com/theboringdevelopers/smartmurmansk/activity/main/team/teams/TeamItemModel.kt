package com.theboringdevelopers.smartmurmansk.activity.main.team.teams

import com.theboringdevelopers.smartmurmansk.R
import com.theboringdevelopers.smartmurmansk.data.model.response.TeamResponse
import com.theboringdevelopers.smartmurmansk.util.ui.ItemViewModel

data class TeamItemModel(
    val team: TeamResponse
) : ItemViewModel {

    override val layoutId: Int
        get() = R.layout.item_team
}