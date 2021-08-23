package com.example.core.toturials.presentation.viewstate

import com.example.core.toturials.presentation.uimodel.TutorialUiModel

sealed class TutorialsViewState {

    object Loading : TutorialsViewState()
    data class onSuccess(val result: List<TutorialUiModel>) : TutorialsViewState()
    data class onError(val error: Throwable) : TutorialsViewState()
}