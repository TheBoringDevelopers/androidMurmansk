package com.theboringdevelopers.smartmurmansk.activity.main.team.ura

import android.view.View
import androidx.navigation.findNavController
import com.theboringdevelopers.smartmurmansk.context.UserContext
import com.theboringdevelopers.smartmurmansk.data.repository.TeamRepository
import com.theboringdevelopers.smartmurmansk.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CelebrationTeamViewModel@Inject constructor(
    val userContext: UserContext,
    val teamRepository: TeamRepository
) : BaseViewModel() {

    fun close(v: View) {
        v.findNavController().navigate(CelebrationTeamFragmentDirections.actionCelebrationCreateFragmentToProfileFragment())
    }

    fun find(v: View) {
        v.findNavController().navigate(CelebrationTeamFragmentDirections.actionCelebrationCreateFragmentToFindTeamFragment())
    }
}