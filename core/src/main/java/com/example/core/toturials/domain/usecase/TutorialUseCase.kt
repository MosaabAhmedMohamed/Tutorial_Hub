package com.example.core.toturials.domain.usecase

import com.example.core.toturials.data.source.local.model.Tutorial
import com.example.core.toturials.domain.repository.TutorialRepository
import io.reactivex.Observable
import javax.inject.Inject

class TutorialUseCase @Inject constructor (private val tutorialRepository: TutorialRepository) {

    fun getTutorials(): Observable<List<Tutorial>> {
        return tutorialRepository.getTutorials()
    }
}