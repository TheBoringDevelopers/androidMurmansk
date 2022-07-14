package com.theboringdevelopers.smartmurmansk.activity.main.team.find

import android.view.View
import androidx.navigation.findNavController
import com.theboringdevelopers.smartmurmansk.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FindTeamViewModel@Inject constructor(

) : BaseViewModel() {

    fun back(v: View) {
        v.findNavController().popBackStack()
    }

}