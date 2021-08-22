package com.example.tutorialhub.presentation.ui.tutorials.adapter

import android.view.View
import com.example.core.toturials.presentation.uimodel.TutorialUiModel
import com.example.tutorialhub.databinding.ItemVideoDownloadedBinding
import com.example.tutorialhub.presentation.ui.base.BaseViewHolder

class VideoDownloadedViewHolder(itemView: View) : BaseViewHolder<TutorialUiModel?>(itemView) {
    private val binding = ItemVideoDownloadedBinding.bind(itemView)

    override fun onBind(item: TutorialUiModel?) {
        binding.tvTitle.text = item?.name
    }
}