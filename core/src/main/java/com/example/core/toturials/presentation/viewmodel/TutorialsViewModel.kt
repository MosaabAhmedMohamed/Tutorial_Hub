package com.example.core.toturials.presentation.viewmodel

import android.app.DownloadManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.base.presentation.BaseViewModel
import com.example.core.toturials.domain.usecase.DownloadTutorialUseCase
import com.example.core.toturials.domain.usecase.TutorialUseCase
import com.example.core.toturials.domain.usecase.UpdateTutorialUseCase
import com.example.core.toturials.presentation.mapping.mapToUIModel
import com.example.core.toturials.presentation.uimodel.TutorialUiModel
import com.example.core.toturials.presentation.viewstate.TutorialsViewState
import com.example.core.util.DownloadStateRetriever
import com.example.core.util.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class TutorialsViewModel @Inject constructor(
    private val tutorialUseCase: TutorialUseCase,
    private val updateTutorialUseCase: UpdateTutorialUseCase,
    private val downloadTutorialUseCase: DownloadTutorialUseCase,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    var tutorialSelectedItem: TutorialUiModel? = null

    private val tutorialsViewStateLDPrivate by lazy { MutableLiveData<TutorialsViewState>() }
    val tutorialsViewStateLD: LiveData<TutorialsViewState> get() = tutorialsViewStateLDPrivate

    private val downloadProgressLDPrivate by lazy { MutableLiveData<Int>() }
    val downloadProgressLD get() = downloadProgressLDPrivate

    fun getTutorials() {
        tutorialUseCase.getTutorials()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnSubscribe {
                tutorialsViewStateLDPrivate.postValue(TutorialsViewState.Loading)
            }
            .subscribe({
                tutorialsViewStateLDPrivate.value = TutorialsViewState.onSuccess(it.mapToUIModel())
            }, {
                tutorialsViewStateLDPrivate.value = TutorialsViewState.onError(it)
            })
            .addTo(compositeDisposable)
    }

    fun downloadFile(
        folderPath: String,
        downloadUrl: String,
        name: String?,
        itemId: Int?,
        onStartDownloading: () -> Unit
    ) {
        val downloadInfo = downloadTutorialUseCase.downloadFile(folderPath, downloadUrl, name)
        if (downloadInfo.first != null && downloadInfo.second != null) {
            checkDownloadProgress(downloadInfo.first!!, downloadInfo.second!!, itemId, folderPath)
            onStartDownloading.invoke()
        }
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
                    downloadProgressLD.value = progressData.second
                }.addTo(compositeDisposable)
        }

    }

    private fun checkIfDownloadCompleted(itemId: Int, progress: Int, folderPath: String) {
        if (progress == 100) {
            tutorialUseCase.getTutorial(itemId)
                .subscribeOn(schedulerProvider.io())
                .subscribe { tutorial ->
                    updateTutorialUseCase.updateTutorialDownloadStatus(
                        tutorial,
                        "$folderPath/${tutorial.name}"
                    )
                }
                .addTo(compositeDisposable)
        }
    }
}

