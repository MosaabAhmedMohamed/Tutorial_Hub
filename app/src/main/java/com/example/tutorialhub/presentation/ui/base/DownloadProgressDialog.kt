package com.example.tutorialhub.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.core.core.di.ViewModelFactory
import com.example.core.toturials.presentation.viewmodel.TutorialsViewModel
import com.example.tutorialhub.databinding.DialogDownloadingBinding
import javax.inject.Inject

class DownloadProgressDialog : BaseDialog() {

    private lateinit var binding: DialogDownloadingBinding

    @Inject
    lateinit var tutorialsViewModelFactory: ViewModelFactory<TutorialsViewModel>
    private val tutorialsViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            tutorialsViewModelFactory
        ).get(TutorialsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogDownloadingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun init() {
        super.init()
        observeProgress()
    }

    private fun observeProgress() {
        tutorialsViewModel.downloadProgressLD.observe(viewLifecycleOwner, {
            if (it == 100) {
                dismiss()
            } else {
                binding.tvCurrentProgress.text = it.toString()
            }
        })
    }

    companion object {
        fun newInstance(): DownloadProgressDialog {
            return DownloadProgressDialog()
        }
    }

}