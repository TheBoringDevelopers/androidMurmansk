package com.theboringdevelopers.smartmurmansk.activity.main.team.teams

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.theboringdevelopers.smartmurmansk.BR

class CreateTeamViewData : BaseObservable() {
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

    @get:Bindable
    var teamId: Long = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.teamId)
        }

    @get:Bindable
    var name: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var city: String = "Мурманск"
        set(value) {
            field = value
            notifyPropertyChanged(BR.city)
        }

    @get:Bindable
    var description: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.description)
        }

    @get:Bindable
    var organization: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.organization)
        }

    @get:Bindable
    var organizationName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.organizationName)
        }
}