package com.example.core.toturials.presentation.viewmodel

import android.app.DownloadManager
import androidx.lifecycle.MutableLiveData
import com.example.core.base.presentation.BaseViewModel
import com.example.core.toturials.domain.usecase.TutorialUseCase
import com.example.core.toturials.presentation.mapping.mapToUIModel
import com.example.core.toturials.presentation.uimodel.TutorialUiModel
import com.example.core.util.DownloadStateRetriever
import com.example.core.util.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class TutorialsViewModel @Inject constructor(
    private val tutorialUseCase: TutorialUseCase,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    var tutorialSelectedItem: TutorialUiModel? = null

    val tutorialsLD by lazy { MutableLiveData<List<TutorialUiModel>>() }
    val progressLD by lazy { MutableLiveData<Int>() }

    fun getTutorials() {
        tutorialUseCase.getTutorials()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe {
                tutorialsLD.value = it.mapToUIModel()
            }
            .addTo(compositeDisposable)
    }

    fun downloadFile(folderPath: String, downloadUrl: String, name: String?, itemId: Int?) {
        val downloadInfo = tutorialUseCase.downloadFile(folderPath, downloadUrl, name)
        checkDownloadProgress(downloadInfo.first, downloadInfo.second, itemId, folderPath)
    }

    private fun checkDownloadProgress(
        dm: DownloadManager,
        downloadId: Long,
        itemId: Int?,
        folderPath: String
    ) {
        itemId?.let { itemId_ ->
            DownloadStateRetriever(dm).retrieve(downloadId, itemId_)
                .filter { it.first == itemId_ }
                .distinctUntilChanged()
                .flatMap {
                    checkIfDownloadCompleted(it.first, it.second, folderPath)
                    return@flatMap Observable.just(it)
                }.observeOn(schedulerProvider.ui())
                .subscribe { progressData ->
                    progressLD.value = progressData.second
                }.addTo(compositeDisposable)
        }

    }

    private fun checkIfDownloadCompleted(itemId: Int, progress: Int, folderPath: String) {
        if (progress == 100) {
            val completedDownloadTutorial = tutorialsLD.value?.find { it.id == itemId }
            tutorialUseCase.updateTutorialDownloadStatus(completedDownloadTutorial, folderPath)
        }
    }
}

