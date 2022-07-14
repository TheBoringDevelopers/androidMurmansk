package com.theboringdevelopers.smartmurmansk.activity.enter.phone

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.theboringdevelopers.smartmurmansk.context.UserContext
import com.theboringdevelopers.smartmurmansk.data.repository.AuthRepository
import com.theboringdevelopers.smartmurmansk.util.BaseViewModel
import com.theboringdevelopers.smartmurmansk.util.Event
import com.theboringdevelopers.smartmurmansk.util.hideKeyboard
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhoneViewModel@Inject constructor(
    val userContext: UserContext,
    val authRepository: AuthRepository,
    @ApplicationContext private val context: Context
) : BaseViewModel() {

    val data = PhoneViewData()

    private val _loginResult = MutableLiveData<Event<AuthRepository.LoginResponse>>()
    val loginResult: LiveData<Event<AuthRepository.LoginResponse>> = _loginResult

    fun back(v: View) {
        v.hideKeyboard()
        v.findNavController().popBackStack()
    }

    fun login(v: View?) {
        viewModelScope.launch(Dispatchers.IO) {
            data.error = false
            data.progress = true
            userContext.clear()
            val login = authRepository.login(
                preparePhone(data.login),
                "123456",
                userContext
            ) { error ->
                errorMessage.postValue(Event(error))
                data.error = true
            }

            if (!data.error) {
                _loginResult.postValue(Event(login))
            }

            data.progress = false
        }
    }

    fun preparePhone(phone: String): String {
        return phone.replace("[ \\-\\+\\(\\)]".toRegex(), "")
    }
}