package com.example.core.toturials.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.core.base.di.AppModule_ProvideSchedulerProviderFactory
import com.example.core.base.presentation.BaseViewModel
import com.example.core.toturials.data.source.local.model.Tutorial
import com.example.core.toturials.domain.usecase.TutorialUseCase
import com.example.core.util.SchedulerProvider
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class TutorialsViewModel @Inject constructor(
    private val tutorialUseCase: TutorialUseCase,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {


    val tutorialsLD by lazy { MutableLiveData<List<Tutorial>>() }
    fun getTutorials() {
        tutorialUseCase.getTutorials()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe {
                tutorialsLD.value = it
            }
            .addTo(compositeDisposable)
    }
}