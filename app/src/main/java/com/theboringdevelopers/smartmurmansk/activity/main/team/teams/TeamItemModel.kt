package com.theboringdevelopers.smartmurmansk.activity.main.team.teams

import com.theboringdevelopers.smartmurmansk.R
import com.theboringdevelopers.smartmurmansk.util.ui.ItemViewModel

data class TeamItemModel(
    val test: String
) : ItemViewModel {

    override val layoutId: Int
        get() = R.layout.item_team
}