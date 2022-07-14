package com.theboringdevelopers.smartmurmansk.di

import android.content.Context
import androidx.room.Room
import com.theboringdevelopers.smartmurmansk.data.local.AppDatabase
import com.theboringdevelopers.smartmurmansk.data.local.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Модуль DI для БД
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Провайдер БД
     */
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "local-db"
        ).enableMultiInstanceInvalidation()
            .fallbackToDestructiveMigration()
            .fallbackToDestructiveMigrationOnDowngrade()
            .build()
    }

    /**
     * Провайдер UsersDao
     */
    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UsersDao {
        return appDatabase.usersDao()
    }
}