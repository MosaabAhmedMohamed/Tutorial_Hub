package com.example.core.toturials.presentation.mapping

import com.example.core.toturials.data.source.local.model.Tutorial
import com.example.core.toturials.presentation.uimodel.TutorialType
import com.example.core.toturials.presentation.uimodel.TutorialUiModel

fun List<Tutorial>.mapToUIModel(): MutableList<TutorialUiModel> {
    val uiModels = mutableListOf<TutorialUiModel>()
    forEach {
        uiModels.add(it.mapToUIModel(it.mapTutorialType()))
    }
    return uiModels
}

fun Tutorial.mapTutorialType(): TutorialType {
    return when (type?.uppercase()) {
        "PDF" -> TutorialType.PDF
        else -> TutorialType.VIDEO
    }
}

fun Tutorial.mapToUIModel(tutorialType: TutorialType): TutorialUiModel {
    return TutorialUiModel(id, tutorialType, url, name, localPath, isDownloaded)
}