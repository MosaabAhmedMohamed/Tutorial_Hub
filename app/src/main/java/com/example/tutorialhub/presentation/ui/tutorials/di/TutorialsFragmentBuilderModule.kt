package com.example.tutorialhub.presentation.ui.tutorials.di

import com.example.tutorialhub.presentation.ui.base.DownloadProgressDialog
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TutorialsFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun provideDownloadProgressDialog(): DownloadProgressDialog
}