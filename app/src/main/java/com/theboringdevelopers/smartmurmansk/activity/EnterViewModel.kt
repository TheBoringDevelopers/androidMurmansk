package com.theboringdevelopers.smartmurmansk.activity

import android.content.Context
import com.theboringdevelopers.smartmurmansk.context.UserContext
import com.theboringdevelopers.smartmurmansk.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class EnterViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val userContext: UserContext
): BaseViewModel() {

    var phoneNumber: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var patronymic: String = ""
    var gender: Boolean = true
    var age: Long = 18

}