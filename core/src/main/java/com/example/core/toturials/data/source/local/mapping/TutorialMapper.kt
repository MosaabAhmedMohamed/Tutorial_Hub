package com.example.core.toturials.data.source.local.mapping

import com.example.core.toturials.data.source.local.model.Tutorial
import com.example.core.toturials.presentation.uimodel.TutorialType
import com.example.core.toturials.presentation.uimodel.TutorialUiModel


fun TutorialUiModel.mapTutorialStringType(): String {
    return when (type) {
        TutorialType.PDF ->  "PDF"
        else -> "Video"
    }
}

fun TutorialUiModel.mapToTutorialLocal(): Tutorial {

    return Tutorial(id, this.mapTutorialStringType(), url, name, localPath, isDownloaded)
}