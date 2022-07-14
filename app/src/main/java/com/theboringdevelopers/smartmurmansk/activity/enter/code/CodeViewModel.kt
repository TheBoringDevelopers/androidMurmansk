package com.theboringdevelopers.smartmurmansk.activity.enter.code

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
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
class CodeViewModel@Inject constructor(
    val userContext: UserContext,
    val authRepository: AuthRepository,
    val state: SavedStateHandle,
    @ApplicationContext private val context: Context
) : BaseViewModel() {

    val data = CodeViewData()

    private val _confirm = MutableLiveData<Event<Any>>()
    val confirm: LiveData<Event<Any>> = _confirm

    var sessionId: String = state.get("sessionId")!!
    var phoneNumber: String = state.get("phoneNumber")!!

    fun back(v: View) {
        v.hideKeyboard()
        v.findNavController().popBackStack()
    }

    fun confirm(code: String) {
        if (data.progress) {
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            data.error = false
            data.progress = true

            try {
                authRepository.registrationConfirm(
                    phoneNumber,
                    code,
                    sessionId)
                authRepository.login(
                    phoneNumber,
                    "123456",
                    userContext
                ) { error ->
                    errorMessage.postValue(Event(error))
                    data.error = true
                }
            } catch (e: Exception) {
                errorMessage.postValue(Event(e.message ?: "Неизвестная ошибка"))
                data.error = true
            }

            if (!data.error) {
                _confirm.postValue(Event(Any()))
            }

            data.progress = false
        }
    }
}