package com.example.tutorialhub.presentation.ui.splash

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.core.core.di.ViewModelFactory
import com.example.core.splash.presentation.viewmodel.SplashViewModel
import com.example.tutorialhub.databinding.ActivitySplashBinding
import com.example.tutorialhub.presentation.ui.base.BaseActivity
import com.example.tutorialhub.presentation.ui.base.ext.navigateToTutorials
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    lateinit var binding: ActivitySplashBinding

    @Inject
    lateinit var tutorialsViewModelFactory: ViewModelFactory<SplashViewModel>
    private val splashViewModel by lazy {
        ViewModelProvider(this, tutorialsViewModelFactory)
            .get(SplashViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        splashViewModel.initTimer()
        observeTimer()
    }

    private fun observeTimer() {
        splashViewModel.timerLD.observe(this,{
            navigateToTutorials()
        })
    }


}