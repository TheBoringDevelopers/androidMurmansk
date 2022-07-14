package com.theboringdevelopers.smartmurmansk.activity.enter.phone

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

import com.theboringdevelopers.smartmurmansk.BR


/**
 * Данные с формы логина
 */
class PhoneViewData : BaseObservable() {

    /** Логин */
    @get:Bindable
    var login = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.login)
        }

    @get:Bindable
    var error = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.error)
        }

    @get:Bindable
    var progress = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.progress)
        }
}