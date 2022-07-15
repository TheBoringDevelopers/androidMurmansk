package com.theboringdevelopers.smartmurmansk.activity.main.team.players.player

import android.util.Log
import android.view.View
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.theboringdevelopers.smartmurmansk.context.UserContext
import com.theboringdevelopers.smartmurmansk.data.model.response.UserResponse
import com.theboringdevelopers.smartmurmansk.data.repository.AuthRepository
import com.theboringdevelopers.smartmurmansk.data.repository.TeamRepository
import com.theboringdevelopers.smartmurmansk.data.repository.UserRepository
import com.theboringdevelopers.smartmurmansk.util.BaseViewModel
import com.theboringdevelopers.smartmurmansk.util.Event
import com.theboringdevelopers.smartmurmansk.util.ui.ItemViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel@Inject constructor(
    val userContext: UserContext,
    val userRepository: UserRepository,
    state: SavedStateHandle
) : BaseViewModel() {

    private val user = state.get<UserResponse>("user")!!
    val data = PlayerViewData()

    init {
        viewModelScope.launch {
            data.description = user.description.toString()
            data.name = user.fio()
            data.age = user.getAgeString()
        }
    }

    fun back(v: View) {
        v.findNavController().popBackStack()
    }
}