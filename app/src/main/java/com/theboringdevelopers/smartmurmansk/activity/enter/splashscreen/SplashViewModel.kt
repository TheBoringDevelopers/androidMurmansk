package com.theboringdevelopers.smartmurmansk.activity.enter.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.theboringdevelopers.smartmurmansk.context.UserContext
import com.theboringdevelopers.smartmurmansk.util.BaseViewModel
import com.theboringdevelopers.smartmurmansk.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userContext: UserContext
) : BaseViewModel() {

    private val _usersIsEmpty = MutableLiveData<Event<Boolean>>()
    val usersIsEmpty: LiveData<Event<Boolean>> = _usersIsEmpty

    init {
        _usersIsEmpty.postValue(Event(false))
    }

}