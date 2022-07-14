package com.theboringdevelopers.smartmurmansk.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.theboringdevelopers.smartmurmansk.data.converter.ListConverter
import com.theboringdevelopers.smartmurmansk.domain.UserEntity

/**
 * Доступ к БД
 */
@Database(
    version = 1,
    exportSchema = true,
    entities = [
        UserEntity::class
    ]
)
@TypeConverters(
    ListConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    /**
     * DAO для учеток
     */
    abstract fun usersDao(): UsersDao
}