package com.example.core.toturials.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.core.base.presentation.BaseViewModel
import com.example.core.toturials.domain.usecase.TutorialUseCase
import com.example.core.toturials.presentation.mapping.mapToUIModel
import com.example.core.toturials.presentation.uimodel.TutorialUiModel
import com.example.core.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class TutorialsViewModel @Inject constructor(
    private val tutorialUseCase: TutorialUseCase,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {


    val tutorialsLD by lazy { MutableLiveData<List<TutorialUiModel>>() }
    fun getTutorials() {
        tutorialUseCase.getTutorials()
            .subscribeOn(schedulerProvider.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                tutorialsLD.value = it.mapToUIModel()
            }
            .addTo(compositeDisposable)
    }
}