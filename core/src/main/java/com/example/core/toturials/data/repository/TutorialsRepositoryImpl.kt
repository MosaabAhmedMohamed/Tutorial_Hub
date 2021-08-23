package com.example.core.toturials.data.repository

import android.app.DownloadManager
import com.example.core.toturials.data.source.local.TutorialLocalDataSource
import com.example.core.toturials.data.source.local.model.Tutorial
import com.example.core.toturials.data.source.remote.DownloadDataSource
import com.example.core.toturials.domain.repository.TutorialRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class TutorialsRepositoryImpl @Inject constructor(
    private val tutorialLocalDataSource: TutorialLocalDataSource,
    private val downloadDataSource: DownloadDataSource
) : TutorialRepository {


    override fun getTutorials(): Flowable<List<Tutorial>> {
        return tutorialLocalDataSource.getTutorials()
    }

    override fun downloadFile(
        folderPath: String,
        downloadUrl: String,
        name: String?
    ): Pair<DownloadManager?, Long?> {
        return downloadDataSource.download(folderPath,downloadUrl,name)
    }

    override fun updateTutorialInLocal(tutorial: Tutorial) {
        tutorialLocalDataSource.updateTutorialInLocal(tutorial)
    }

    override fun getTutorial(id: Int): Single<Tutorial> {
      return tutorialLocalDataSource.getTutorial(id)
    }

}