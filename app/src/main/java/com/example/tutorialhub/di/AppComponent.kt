package com.example.tutorialhub.di

import android.app.Application
import com.example.core.core.di.AppModule
import com.example.tutorialhub.app.TutorialHubApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class]
)

interface AppComponent {
    fun inject(app: TutorialHubApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}