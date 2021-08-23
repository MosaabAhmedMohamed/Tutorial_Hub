package com.example.core.toturials.domain.usecase

import com.example.core.toturials.data.source.local.model.Tutorial
import com.example.core.toturials.domain.repository.TutorialRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class TutorialUseCase @Inject constructor(private val tutorialRepository: TutorialRepository) {

    fun getTutorials(): Flowable<List<Tutorial>> {
        return tutorialRepository.getTutorials()
    }

    fun getTutorial(id: Int): Single<Tutorial> {
        return tutorialRepository.getTutorial(id)
    }


}