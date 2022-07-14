package com.theboringdevelopers.smartmurmansk.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Пользователь
 */
@Entity(
    tableName = "users"
)
class UserEntity(
    @ColumnInfo(name = "uid") @PrimaryKey var uid: Int,
    @ColumnInfo(name = "access_token") var access_token: String? = null,
    @ColumnInfo(name = "username") var username: String? = null
)