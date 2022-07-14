package com.theboringdevelopers.smartmurmansk.util

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {

    /** Флаг прогресса */
    val progress = ObservableBoolean(false)

    /** Глобальное сообщение об ошибке */
    val errorMessage: MutableLiveData<Event<String>> = MutableLiveData()

    /** Глобальное сообщение */
    val infoMessage: MutableLiveData<Event<String>> = MutableLiveData()

    /** Результат */
    protected val _result = MutableLiveData<Event<Any>>()
    val result: LiveData<Event<Any>> = _result
}