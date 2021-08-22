package com.example.tutorialhub.presentation.ui.tutorials.adapter

import android.view.View
import com.example.core.toturials.presentation.uimodel.TutorialUiModel
import com.example.tutorialhub.databinding.ItemPdfDownloadedBinding
import com.example.tutorialhub.presentation.ui.base.BaseViewHolder

class PDFDownloadedViewHolder(itemView: View) : BaseViewHolder<TutorialUiModel?>(itemView) {

    private val binding = ItemPdfDownloadedBinding.bind(itemView)

    override fun onBind(item: TutorialUiModel?) {
        binding.tvTitle.text = item?.name
    }
}