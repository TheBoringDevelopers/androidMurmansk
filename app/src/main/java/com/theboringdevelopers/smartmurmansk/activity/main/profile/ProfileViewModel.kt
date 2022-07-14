package com.theboringdevelopers.smartmurmansk.activity.main.profile

import android.view.View
import androidx.navigation.findNavController
import com.theboringdevelopers.smartmurmansk.context.UserContext
import com.theboringdevelopers.smartmurmansk.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel@Inject constructor(
    val userContext: UserContext
) : BaseViewModel() {

    fun createTeam(v: View) {
        v.findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToCreateTeamFragment())
    }

    fun findTeam(v: View) {
        v.findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToFindTeamFragment())
    }
}