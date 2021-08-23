package com.example.tutorialhub.presentation.ui.tutorials.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.toturials.presentation.uimodel.TutorialType
import com.example.core.toturials.presentation.uimodel.TutorialUiModel
import com.example.tutorialhub.R

class TutorialsAdapter(
    private val downloadClickAction: (item: TutorialUiModel) -> Unit,
    private val openClickAction: (item: TutorialUiModel) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var tutorials: List<TutorialUiModel?> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            PDF_DOWNLOADED_VIEW -> {
                PDFDownloadedViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_pdf_downloaded, parent, false)
                )
            }
            PDF_DOWNLOAD_VIEW -> {
                PDFDownloadViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_pdf_download, parent, false)
                )
            }
            VIDEO_DOWNLOAD_VIEW -> {
                VideoDownloadViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_video_download, parent, false)
                )
            }
            else -> VideoDownloadedViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_video_downloaded, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = tutorials[position]
        handleBinding(position, holder, item)
        handleItemClick(holder, position)
    }

    private fun handleItemClick(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (getItemViewType(position)) {
            PDF_DOWNLOAD_VIEW, VIDEO_DOWNLOAD_VIEW -> {
                holder.itemView.setOnClickListener {
                    tutorials[position]?.let {
                        downloadClickAction.invoke(it)
                    }
                }
            }
            else -> {
                holder.itemView.setOnClickListener {
                    tutorials[position]?.let {
                        openClickAction.invoke(it)
                    }
                }
            }
        }

    }

    private fun handleBinding(
        position: Int,
        holder: RecyclerView.ViewHolder,
        item: TutorialUiModel?
    ) {
        when (getItemViewType(position)) {
            PDF_DOWNLOADED_VIEW -> {
                (holder as PDFDownloadedViewHolder).onBind(item)
            }
            PDF_DOWNLOAD_VIEW -> {
                (holder as PDFDownloadViewHolder).onBind(item)
            }
            VIDEO_DOWNLOAD_VIEW -> {
                (holder as VideoDownloadViewHolder).onBind(item)
            }
            else -> {
                (holder as VideoDownloadedViewHolder).onBind(item)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(result: List<TutorialUiModel>) {
        tutorials = result
        notifyDataSetChanged()
    }

    override fun getItemCount() = tutorials.size ?: 0

    override fun getItemViewType(position: Int): Int {
        val item = tutorials[position]
        return when (item?.type) {
            TutorialType.PDF -> getPDFViewType(item.isDownloaded)
            else -> getVIDEOViewType(item?.isDownloaded ?: false)
        }
    }

    private fun getPDFViewType(downloaded: Boolean) =
        if (downloaded) PDF_DOWNLOADED_VIEW else PDF_DOWNLOAD_VIEW

    private fun getVIDEOViewType(downloaded: Boolean) =
        if (downloaded) VIDEO_DOWNLOADED_VIEW else VIDEO_DOWNLOAD_VIEW

    companion object {
        const val PDF_DOWNLOADED_VIEW = 1
        const val PDF_DOWNLOAD_VIEW = 2
        const val VIDEO_DOWNLOADED_VIEW = 3
        const val VIDEO_DOWNLOAD_VIEW = 4
    }


}