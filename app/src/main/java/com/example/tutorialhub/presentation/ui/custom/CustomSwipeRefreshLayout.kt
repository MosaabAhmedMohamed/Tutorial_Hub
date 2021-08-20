package com.example.tutorialhub.presentation.ui.custom

import android.content.Context
import android.util.AttributeSet
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class CustomSwipeRefreshLayout(context: Context, attrs: AttributeSet?) :
    SwipeRefreshLayout(context, attrs) {

    fun init(){
        setColorSchemeResources(android.R.color.black,
            android.R.color.holo_orange_dark,
            android.R.color.darker_gray)
    }

    fun stopRefresh(){
        if (isRefreshing) isRefreshing = false
    }


}