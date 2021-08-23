package com.example.core.toturials.domain.usecase

import android.app.DownloadManager
import com.example.core.toturials.data.source.local.mapping.mapToTutorialLocal
import com.example.core.toturials.data.source.local.model.Tutorial
import com.example.core.toturials.domain.repository.TutorialRepository
import com.example.core.toturials.presentation.uimodel.TutorialUiModel
import io.reactivex.Flowable
import javax.inject.Inject

class TutorialUseCase @Inject constructor(private val tutorialRepository: TutorialRepository) {

    fun getTutorials(): Flowable<List<Tutorial>> {
        return tutorialRepository.getTutorials()
    }

    fun downloadFile(
        folderPath: String,
        downloadUrl: String,
        name: String?
    ): Pair<DownloadManager, Long> {
        return tutorialRepository.downloadFile(folderPath, downloadUrl, name)
    }

    fun updateTutorialDownloadStatus(
        tutorial: TutorialUiModel?,
        folderPath: String
    ) {
        tutorial?.let {
             setFilePathAndStatus(tutorial,folderPath)
             tutorialRepository.updateTutorialInLocal(tutorial.mapToTutorialLocal())
        }
    }

    private fun setFilePathAndStatus(tutorial: TutorialUiModel, folderPath: String) {
        tutorial.localPath = folderPath
        tutorial.isDownloaded = true
    }

}