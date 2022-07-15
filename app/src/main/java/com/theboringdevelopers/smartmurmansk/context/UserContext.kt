package com.theboringdevelopers.smartmurmansk.context

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserContext (
    var userId: Long? = null,
    var name: String = "",
    var lastName: String = "",
    var patronymic: String = "",
    var age: Long = 0,
    var gender: Boolean = false,
    var login: String? = null,
    var accessToken: String? = null,
    var refreshToken: String? = null,
    var userRole: String? = null,
) : Parcelable {

    fun copy(from: UserContext) {
        userId = from.userId
        name = from.name
        lastName = from.lastName
        patronymic = from.patronymic
        age = from.age
        gender = from.gender
        login = from.login
        accessToken = from.accessToken
        refreshToken = from.refreshToken
        userRole = from.userRole
    }

    fun getFio() : String = "$name $lastName $patronymic"

    fun clear() {
        userId = null
        login = null
        accessToken = null
        refreshToken = null
        userRole = null
    }

}