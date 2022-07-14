package com.theboringdevelopers.smartmurmansk.di

import android.content.Context
import com.theboringdevelopers.smartmurmansk.context.UserContext
import com.theboringdevelopers.smartmurmansk.data.remote.ServerApi
import com.theboringdevelopers.smartmurmansk.data.repository.AuthRepository
import com.theboringdevelopers.smartmurmansk.data.repository.TeamRepository
import com.theboringdevelopers.smartmurmansk.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped

/**
 * Модуль DI для репозиториев
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    /**
     * Провайдер ProfileRepository
     */
    @Provides
    @ActivityRetainedScoped
    fun provideUserRepository(
        userContext: UserContext,
        serverApi: ServerApi,
    ): UserRepository =
        UserRepository(serverApi, userContext)

    @Provides
    @ActivityRetainedScoped
    fun provideAuthRepository(
        @ApplicationContext appContext: Context,
        serverApi: ServerApi,
    ): AuthRepository =
        AuthRepository(serverApi, appContext)

    @Provides
    @ActivityRetainedScoped
    fun provideTeamRepository(
        @ApplicationContext appContext: Context,
        serverApi: ServerApi,
    ): TeamRepository =
        TeamRepository(serverApi, appContext)

}