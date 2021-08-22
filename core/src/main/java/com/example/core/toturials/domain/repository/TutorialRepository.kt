package com.example.core.toturials.domain.repository

import com.example.core.toturials.data.source.local.model.Tutorial
import io.reactivex.Flowable
import io.reactivex.Observable

interface TutorialRepository {

    fun getTutorials(): Flowable<List<Tutorial>>


}