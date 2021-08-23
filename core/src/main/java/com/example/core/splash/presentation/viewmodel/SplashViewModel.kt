package com.example.core.splash.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.base.presentation.BaseViewModel
import com.example.core.util.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {


    private val timerLDPrivate by lazy { MutableLiveData<Boolean>() }
    val timerLD: LiveData<Boolean> get() = timerLDPrivate

    fun initTimer() {
        Observable.timer(1500, TimeUnit.MILLISECONDS)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe {
                timerLDPrivate.value = true
            }.addTo(compositeDisposable)

    }
}