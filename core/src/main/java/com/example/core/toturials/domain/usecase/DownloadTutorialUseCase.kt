package com.example.core.toturials.domain.usecase

import android.app.DownloadManager
import com.example.core.toturials.domain.repository.TutorialRepository
import javax.inject.Inject

class DownloadTutorialUseCase @Inject constructor(private val tutorialRepository: TutorialRepository) {

    fun downloadFile(
        folderPath: String,
        downloadUrl: String,
        name: String?
    ): Pair<DownloadManager?, Long?> {
        return tutorialRepository.downloadFile(folderPath, downloadUrl, name)
    }
}