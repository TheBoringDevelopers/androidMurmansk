package com.theboringdevelopers.smartmurmansk.di

import com.theboringdevelopers.smartmurmansk.context.UserContext
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

/**
 * Модуль DI для UserContext
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
object ContextModule {

    /**
     * Провайдер UserContext
     */
    @Provides
    @ActivityRetainedScoped
    fun provideUserContext() : UserContext = UserContext()

}