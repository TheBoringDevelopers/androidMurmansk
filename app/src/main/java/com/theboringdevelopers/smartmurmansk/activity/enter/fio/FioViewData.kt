package com.theboringdevelopers.smartmurmansk.activity.enter.fio

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.theboringdevelopers.smartmurmansk.BR

class FioViewData : BaseObservable() {

    @get:Bindable
    var lastName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.lastName)
        }

    @get:Bindable
    var name: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var patronymic: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.patronymic)
        }
}