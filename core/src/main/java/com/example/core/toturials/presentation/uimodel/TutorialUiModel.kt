package com.example.core.toturials.presentation.uimodel

data class TutorialUiModel(
    val id: Int? = 0,
    val type: TutorialType?,
    val url: String?,
    val name: String?,
    val localPath: String?,
    val isDownloaded: Boolean = false
)
