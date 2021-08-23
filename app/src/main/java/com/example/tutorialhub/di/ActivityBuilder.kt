package com.example.tutorialhub.di

import com.example.core.toturials.di.TutorialsModule
import com.example.tutorialhub.presentation.ui.splash.SplashActivity
import com.example.tutorialhub.presentation.ui.tutorials.di.TutorialsFragmentBuilderModule
import com.example.tutorialhub.presentation.ui.tutorials.ui.activity.TutorialsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(
        modules = [TutorialsModule::class, TutorialsFragmentBuilderModule::class]
    )
    abstract fun binTutorialsActivity(): TutorialsActivity


    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

}