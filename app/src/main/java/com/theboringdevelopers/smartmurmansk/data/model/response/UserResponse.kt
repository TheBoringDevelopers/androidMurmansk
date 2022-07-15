package com.theboringdevelopers.smartmurmansk.data.model.response

import android.os.Parcelable
import com.theboringdevelopers.smartmurmansk.data.model.bean.Image
import com.theboringdevelopers.smartmurmansk.data.model.bean.SportType
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserResponse(
    val id: Long,
    val firstName: String?,
    val lastName: String?,
    val patronymic: String?,
    val age: Long?,
    val city: String?,
    val gender: Boolean,
    val description: String?,
    val sportTypes: List<SportType>?,
    val image: Image?
) : Parcelable {
    fun fio() = "$firstName $lastName"

    fun getAgeString() = "$age лет"
}