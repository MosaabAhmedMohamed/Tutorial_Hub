package com.example.tutorialhub.presentation.ui.tutorials.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.base.di.ViewModelFactory
import com.example.core.toturials.data.source.local.model.Tutorial
import com.example.core.toturials.presentation.viewmodel.TutorialsViewModel
import com.example.tutorialhub.databinding.ActivityTutorialsBinding
import com.example.tutorialhub.presentation.ui.base.BaseActivity
import com.example.tutorialhub.presentation.ui.tutorials.adapter.TutorialsAdapter
import javax.inject.Inject

class TutorialsActivity : BaseActivity() {

    lateinit var binding: ActivityTutorialsBinding

    @Inject
    lateinit var tutorialsViewModelFactory: ViewModelFactory<TutorialsViewModel>
    private val tutorialsViewModel by lazy {
        ViewModelProvider(this, tutorialsViewModelFactory).get(TutorialsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tutorialsViewModel.getTutorials()

        tutorialsViewModel.tutorialsLD.observe(this,{
            initTutorialsRV(it)
        })
        
    }

    private fun initTutorialsRV(list: List<Tutorial>) {
        val adapter = TutorialsAdapter(list)
        binding.rvTutorials.layoutManager =
            GridLayoutManager(this, 3)
        binding.rvTutorials.adapter = adapter
    }
}