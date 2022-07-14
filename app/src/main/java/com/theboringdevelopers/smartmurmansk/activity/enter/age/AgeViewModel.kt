package com.theboringdevelopers.smartmurmansk.activity.enter.age

import android.view.View
import androidx.navigation.findNavController
import com.theboringdevelopers.smartmurmansk.util.BaseViewModel
import com.theboringdevelopers.smartmurmansk.util.hideKeyboard
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AgeViewModel@Inject constructor(

) : BaseViewModel() {

    fun back(v: View) {
        v.hideKeyboard()
        v.findNavController().popBackStack()
    }

    fun continueButton(v: View) {
        v.findNavController().navigate(AgeFragmentDirections.actionAgeFragmentToPersonalPreferencesFragment())
    }
}