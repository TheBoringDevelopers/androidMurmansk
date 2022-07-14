package com.theboringdevelopers.smartmurmansk.activity.enter.info

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.theboringdevelopers.smartmurmansk.context.UserContext
import com.theboringdevelopers.smartmurmansk.data.repository.UserRepository
import com.theboringdevelopers.smartmurmansk.util.BaseViewModel
import com.theboringdevelopers.smartmurmansk.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel@Inject constructor(
    val userContext: UserContext,
    val userRepository: UserRepository
) : BaseViewModel() {

    private val _changePage = MutableLiveData<Event<Any>>()
    val changePage: LiveData<Event<Any>> = _changePage

    private val _back = MutableLiveData<Event<Any>>()
    val back: LiveData<Event<Any>> = _back

    private val _success = MutableLiveData<Event<Any>>()
    val success: LiveData<Event<Any>> = _success

    var currentPage: Int = 0

    fun back(v: View) {
        _back.postValue(Event(Any()))
    }

    fun continueButton(v: View) {
        _changePage.postValue(Event(Any()))
    }

    fun updateInfo(
        phoneNumber: String,
        firstName: String,
        lastName: String,
        patronymic: String,
        age: Long,
        gender: Boolean
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                progress.set(true)
                userRepository.updateUserInfo(
                    phoneNumber, firstName, lastName, patronymic, age, gender
                )
                userContext.login = phoneNumber
                userContext.name = firstName
                userContext.lastName = lastName
                userContext.gender = gender
                userContext.age = age
                userContext.patronymic = patronymic
                _success.postValue(Event(Any()))
            } catch (e: Exception) {
                errorMessage.postValue(Event(e.message ?: "Ошибка"))
                progress.set(false)
            }
        }
    }
}