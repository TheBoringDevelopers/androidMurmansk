package com.theboringdevelopers.smartmurmansk.data.local

import androidx.room.*
import com.theboringdevelopers.smartmurmansk.domain.UserEntity

/**
 * Доступ к USERS
 */
@Dao
interface UsersDao {

    /**
     * Получить все учетки
     */
    @Query("SELECT * FROM users")
    suspend fun getAll(): List<UserEntity>

    /**
     * Число учеток в системе
     */
    @Query("SELECT COUNT(*) FROM users")
    suspend fun count(): Int

    /**
     * Загрузить учетку по ID
     */
    @Query("SELECT * FROM users WHERE uid = :userId")
    suspend fun loadById(userId: Long): UserEntity

    /**
     * Добавить учетку
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userEntity: UserEntity)

    /**
     * Обновить
     */
    @Update
    suspend fun update(userEntity: UserEntity)

    /**
     * Удалить учетку
     */
    @Query("DELETE FROM users WHERE uid = :id")
    suspend fun delete(id: Long)


    @Query("DELETE FROM users")
    suspend fun deleteAll()
}