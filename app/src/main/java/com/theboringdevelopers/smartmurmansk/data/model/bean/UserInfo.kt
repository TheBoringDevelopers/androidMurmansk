package com.theboringdevelopers.smartmurmansk.data.model.bean

data class UserInfo(
    val phoneNumber: String,
    val password: String? = null,
    val firstName: String,
    val lastName: String,
    val patronymic: String,
    val age: Long,
    val gender: Boolean,
    val email: String? = null,
)
