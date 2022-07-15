package com.theboringdevelopers.smartmurmansk.activity.main.team.players

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.theboringdevelopers.smartmurmansk.activity.main.team.find.FindTeamFragmentDirections
import com.theboringdevelopers.smartmurmansk.context.UserContext
import com.theboringdevelopers.smartmurmansk.data.repository.AuthRepository
import com.theboringdevelopers.smartmurmansk.data.repository.TeamRepository
import com.theboringdevelopers.smartmurmansk.util.BaseViewModel
import com.theboringdevelopers.smartmurmansk.util.Event
import com.theboringdevelopers.smartmurmansk.util.ui.ItemViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel@Inject constructor(
    val userContext: UserContext,
    val teamRepository: TeamRepository
) : BaseViewModel() {

    private val _data = MutableLiveData<List<ItemViewModel>>(emptyList())
    val data: LiveData<List<ItemViewModel>> = _data

    init {
        viewModelScope.launch(Dispatchers.IO) {
            progress.set(true)
            val viewData = mutableListOf<ItemViewModel>()
            val containers = teamRepository.users() {
                errorMessage.postValue(Event(it))
            }
            viewData.addAll(containers.map { PlayerItemModel(it) })
            _data.postValue(viewData)
            progress.set(false)
        }
    }

    fun enter(v: View, item: ItemViewModel) {
        v.findNavController().navigate(FindTeamFragmentDirections.actionFindTeamFragmentToPlayerFragment((item as PlayerItemModel).player))
    }

}