package com.theboringdevelopers.smartmurmansk.activity.main.team

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.theboringdevelopers.smartmurmansk.activity.main.team.teams.CreateTeamViewData
import com.theboringdevelopers.smartmurmansk.context.UserContext
import com.theboringdevelopers.smartmurmansk.data.model.bean.Team
import com.theboringdevelopers.smartmurmansk.data.repository.AuthRepository
import com.theboringdevelopers.smartmurmansk.data.repository.TeamRepository
import com.theboringdevelopers.smartmurmansk.util.BaseViewModel
import com.theboringdevelopers.smartmurmansk.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class CreateTeamViewModel@Inject constructor(
    val userContext: UserContext,
    val teamRepository: TeamRepository
) : BaseViewModel() {

    val data = CreateTeamViewData()

    private val _confirm = MutableLiveData<Event<Any>>()
    val confirm: LiveData<Event<Any>> = _confirm

    fun create(v: View) {
        if (data.progress) {
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            data.error = false
            data.progress = true

            try {
                teamRepository.create(
                    userContext.userId!!,
                    Team(
                        data.teamId,
                        data.name,
                        data.city,
                        data.description,
                        data.organization,
                        data.organizationName
                    )
                )
            }  catch (e: Exception) {
                errorMessage.postValue(Event(e.message ?: "Неизвестная ошибка"))
                data.error = true
            }

            if (!data.error) {
                _confirm.postValue(Event(Any()))
            }
            data.progress = false
        }
    }

    fun selectSports(v: View) {
        v.findNavController().navigate(CreateTeamFragmentDirections.actionCreateTeamFragmentToMoreSheetFragment())
    }

    fun org(v: View) {
        data.organization = !data.organization
    }


}