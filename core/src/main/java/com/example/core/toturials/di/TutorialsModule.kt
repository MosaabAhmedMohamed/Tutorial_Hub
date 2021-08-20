package com.example.core.toturials.di

import com.example.core.toturials.data.repository.TutorialsRepositoryImpl
import com.example.core.toturials.data.source.local.TutorialLocalDataSource
import com.example.core.toturials.domain.repository.TutorialRepository
import dagger.Module
import dagger.Provides


@Module
class TutorialsModule {

    @Provides
    fun provideTutorialsRepository(tutorialLocalDataSource: TutorialLocalDataSource): TutorialRepository =
        TutorialsRepositoryImpl(tutorialLocalDataSource)

}