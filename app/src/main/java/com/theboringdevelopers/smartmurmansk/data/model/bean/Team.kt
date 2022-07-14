package com.theboringdevelopers.smartmurmansk.data.model.bean

data class Team(
    val id: Long,
    val name: String,
    val city: String,
    val description: String,
    val organization: Boolean,
    val organizationName: String?
)
