package com.theboringdevelopers.smartmurmansk.activity.main.team.players.player

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.theboringdevelopers.smartmurmansk.BR

class PlayerViewData : BaseObservable() {

    @get:Bindable
    var name: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var description: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.description)
        }

    @get:Bindable
    var age: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.age)
        }

}