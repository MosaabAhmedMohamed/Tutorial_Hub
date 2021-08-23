package com.example.core.toturials.domain.usecase

import com.example.core.toturials.data.source.local.model.Tutorial
import com.example.core.toturials.domain.repository.TutorialRepository
import javax.inject.Inject

class UpdateTutorialUseCase @Inject constructor(private val tutorialRepository: TutorialRepository) {

    fun updateTutorialDownloadStatus(
        tutorial: Tutorial?,
        folderPath: String
    ) {
        tutorial?.let {
            setFilePathAndStatus(tutorial, folderPath)
            tutorialRepository.updateTutorialInLocal(tutorial)
        }
    }

    private fun setFilePathAndStatus(tutorial: Tutorial, folderPath: String) {
        tutorial.localPath = folderPath
        tutorial.isDownloaded = true
    }
}