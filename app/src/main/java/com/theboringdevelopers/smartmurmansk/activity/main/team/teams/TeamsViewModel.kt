package com.theboringdevelopers.smartmurmansk.activity.main.team.teams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.theboringdevelopers.smartmurmansk.activity.main.team.players.PlayerItemModel
import com.theboringdevelopers.smartmurmansk.context.UserContext
import com.theboringdevelopers.smartmurmansk.data.repository.TeamRepository
import com.theboringdevelopers.smartmurmansk.util.BaseViewModel
import com.theboringdevelopers.smartmurmansk.util.Event
import com.theboringdevelopers.smartmurmansk.util.ui.ItemViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel@Inject constructor(
    val userContext: UserContext,
    val teamRepository: TeamRepository
) : BaseViewModel() {

    private val _data = MutableLiveData<List<ItemViewModel>>(emptyList())
    val data: LiveData<List<ItemViewModel>> = _data

    init {
        viewModelScope.launch(Dispatchers.IO) {
            progress.set(true)
            val viewData = mutableListOf<ItemViewModel>()
            val containers = teamRepository.teams {
                errorMessage.postValue(Event(it))
            }
            viewData.addAll(containers.map { TeamItemModel(it) })
            _data.postValue(viewData)
            progress.set(false)
        }
    }

}