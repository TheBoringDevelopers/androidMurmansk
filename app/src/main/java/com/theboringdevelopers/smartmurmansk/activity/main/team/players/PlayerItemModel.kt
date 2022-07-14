package com.theboringdevelopers.smartmurmansk.activity.main.team.players

import com.theboringdevelopers.smartmurmansk.R
import com.theboringdevelopers.smartmurmansk.data.model.response.UserResponse
import com.theboringdevelopers.smartmurmansk.util.ui.ItemViewModel

data class PlayerItemModel(
    val player: UserResponse
) : ItemViewModel {

    override val layoutId: Int
        get() = R.layout.item_player
}