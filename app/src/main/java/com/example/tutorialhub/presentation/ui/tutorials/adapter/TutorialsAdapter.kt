package com.example.tutorialhub.presentation.ui.tutorials.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.toturials.data.source.local.model.Tutorial
import com.example.tutorialhub.R

class TutorialsAdapter( var tutorials: List<Tutorial?>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == PDF_VIEW) {
            PDFViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_pdf, parent, false)
            )
        } else
            VideoViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_video, parent, false))


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            if (PDF_VIEW == getItemViewType(position)) {
                tutorials[position]?.let { (holder as PDFViewHolder).onBind(it) }
            } else {
                tutorials[position]?.let { (holder as VideoViewHolder).onBind(it) }
              /*  holder.itemView.setOnClickListener {
                    itemClickLiveData.value = data[position]
                }*/
            }


    }

    override fun getItemCount(): Int {
        return tutorials?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (tutorials?.get(position)!!.type.uppercase() == "PDF") {
            PDF_VIEW
        } else {
            VIDEO_VIEW
        }
    }

    companion object {
        const val PDF_VIEW = 1
        const val VIDEO_VIEW = 2
    }


}