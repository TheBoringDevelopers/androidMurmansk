package com.theboringdevelopers.smartmurmansk.activity.enter.code

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

import com.theboringdevelopers.smartmurmansk.BR


class CodeViewData : BaseObservable() {

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