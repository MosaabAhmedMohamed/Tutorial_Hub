package com.example.core.toturials.domain.repository

import android.app.DownloadManager
import com.example.core.toturials.data.source.local.model.Tutorial
import io.reactivex.Flowable
import io.reactivex.Single

interface TutorialRepository {

    fun getTutorials(): Flowable<List<Tutorial>>

    fun downloadFile(
        folderPath: String,
        downloadUrl: String,
        name: String?
    ): Pair<DownloadManager?, Long?>

    fun updateTutorialInLocal(tutorial: Tutorial)

    fun getTutorial(id: Int): Single<Tutorial>
}