package com.example.core.toturials.data.repository

import com.example.core.toturials.data.source.local.TutorialLocalDataSource
import com.example.core.toturials.data.source.local.model.Tutorial
import com.example.core.toturials.domain.repository.TutorialRepository
import io.reactivex.Observable
import javax.inject.Inject

class TutorialsRepositoryImpl @Inject constructor(
    private val tutorialLocalDataSource: TutorialLocalDataSource
) : TutorialRepository {


    override fun getTutorials(): Observable<List<Tutorial>> {
       return tutorialLocalDataSource.getTutorials()
    }

}