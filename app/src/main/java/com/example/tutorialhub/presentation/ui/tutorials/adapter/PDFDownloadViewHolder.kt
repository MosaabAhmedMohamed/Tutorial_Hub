package com.example.tutorialhub.presentation.ui.tutorials.adapter

import android.view.View
import com.example.core.toturials.presentation.uimodel.TutorialUiModel
import com.example.tutorialhub.databinding.ItemPdfDownloadBinding
import com.example.tutorialhub.presentation.ui.base.BaseViewHolder

class PDFDownloadViewHolder(itemView: View) : BaseViewHolder<TutorialUiModel?>(itemView) {

    private val binding = ItemPdfDownloadBinding.bind(itemView)

    override fun onBind(item: TutorialUiModel?) {
        binding.tvTitle.text = item?.name
    }
}