package com.example.core.toturials.di

import android.content.Context
import com.example.core.core.db.TutorialsHubDatabase
import com.example.core.toturials.data.repository.TutorialsRepositoryImpl
import com.example.core.toturials.data.source.local.TutorialLocalDataSource
import com.example.core.toturials.data.source.remote.DownloadDataSource
import com.example.core.toturials.domain.repository.TutorialRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides


@Module
class TutorialsModule {


    @Provides
    fun provideTutorialLocalDataSource(
        context: Context,
        db: TutorialsHubDatabase,
        gson: Gson
    ): TutorialLocalDataSource =
        TutorialLocalDataSource(
            context,
            db.tutorialsDao(),
            gson
        )

    @Provides
    fun provideDownloadDataSource(
        context: Context
    ): DownloadDataSource =
        DownloadDataSource(context)

    @Provides
    fun provideTutorialsRepository(
        tutorialLocalDataSource: TutorialLocalDataSource,
        downloadDataSource: DownloadDataSource
    ): TutorialRepository =
        TutorialsRepositoryImpl(tutorialLocalDataSource, downloadDataSource)

}