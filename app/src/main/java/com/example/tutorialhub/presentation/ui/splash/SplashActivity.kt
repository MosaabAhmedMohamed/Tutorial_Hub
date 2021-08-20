package com.example.tutorialhub.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import com.example.tutorialhub.R
import com.example.tutorialhub.presentation.ui.base.BaseActivity
import com.example.tutorialhub.presentation.ui.tutorials.activity.TutorialsActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this, TutorialsActivity::class.java))

    }
}