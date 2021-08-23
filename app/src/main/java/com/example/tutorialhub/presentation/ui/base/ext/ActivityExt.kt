package com.example.tutorialhub.presentation.ui.base.ext

import android.app.Activity
import android.content.Intent
import com.example.tutorialhub.presentation.ui.base.BaseActivity
import com.example.tutorialhub.presentation.ui.custom.LoadingProgressDialog.Companion.showLoadingDialog
import com.example.tutorialhub.presentation.ui.custom.RetryDialog
import com.example.tutorialhub.presentation.ui.tutorials.ui.activity.TutorialsActivity


fun BaseActivity.hideLoading() {
    if (mProgressDialog != null && mProgressDialog!!.isShowing) {
        mProgressDialog!!.cancel()
    }
}

fun BaseActivity.showLoading() {
    hideLoading()
    mProgressDialog = showLoadingDialog(this)
}

fun BaseActivity.showRetryDialog(retry: Boolean, onRetry: () -> Unit) {
    if (retry) RetryDialog.newInstance(onRetry)
        .show(supportFragmentManager, RetryDialog::class.simpleName)
}

