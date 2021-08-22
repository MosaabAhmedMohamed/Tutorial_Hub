package com.example.tutorialhub.presentation.ui.tutorials.adapter

import android.view.View
import com.example.core.toturials.presentation.uimodel.TutorialUiModel
import com.example.tutorialhub.databinding.ItemVideoDownloadBinding
import com.example.tutorialhub.presentation.ui.base.BaseViewHolder

class VideoDownloadViewHolder(itemView: View) : BaseViewHolder<TutorialUiModel?>(itemView) {
    private val binding = ItemVideoDownloadBinding.bind(itemView)

    override fun onBind(item: TutorialUiModel?) {
        binding.tvTitle.text = item?.name
    }
}