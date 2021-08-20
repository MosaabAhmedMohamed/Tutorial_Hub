package com.example.tutorialhub.presentation.ui.base

import android.app.ProgressDialog
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity(){

    internal var mProgressDialog: ProgressDialog? = null


}