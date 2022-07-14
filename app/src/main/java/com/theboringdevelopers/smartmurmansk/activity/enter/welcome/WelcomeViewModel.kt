package com.theboringdevelopers.smartmurmansk.activity.enter.welcome

import android.view.View
import androidx.navigation.findNavController
import com.theboringdevelopers.smartmurmansk.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel@Inject constructor(

) : BaseViewModel() {

    fun start(v: View) {
        v.findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToPhoneFragment())
    }

}