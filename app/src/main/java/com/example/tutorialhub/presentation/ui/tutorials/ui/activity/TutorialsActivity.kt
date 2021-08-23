package com.example.tutorialhub.presentation.ui.tutorials.ui.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.core.di.ViewModelFactory
import com.example.core.toturials.presentation.uimodel.TutorialUiModel
import com.example.core.toturials.presentation.viewmodel.TutorialsViewModel
import com.example.core.toturials.presentation.viewstate.TutorialsViewState
import com.example.tutorialhub.databinding.ActivityTutorialsBinding
import com.example.tutorialhub.presentation.ui.base.BaseActivity
import com.example.tutorialhub.presentation.ui.tutorials.ui.adapter.TutorialsAdapter
import javax.inject.Inject
import com.example.tutorialhub.presentation.ui.base.DownloadProgressDialog
import com.example.tutorialhub.presentation.ui.base.ext.*
import com.example.core.toturials.presentation.uimodel.TutorialType


class TutorialsActivity : BaseActivity() {

    lateinit var binding: ActivityTutorialsBinding

    private val tutorialsAdapter = TutorialsAdapter({
        onIemDownloadClickAction(it)
    }, {
        onIemOpenClickAction(it)
    })

    @Inject
    lateinit var tutorialsViewModelFactory: ViewModelFactory<TutorialsViewModel>
    private val tutorialsViewModel by lazy {
        ViewModelProvider(this, tutorialsViewModelFactory).get(TutorialsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initTutorialsRV()
        tutorialsViewModel.getTutorials()
        observeViewState()
    }

    private fun initTutorialsRV() {
        binding.rvTutorials.layoutManager =
            GridLayoutManager(this, 2)
        binding.rvTutorials.adapter = tutorialsAdapter
    }

    private fun observeViewState() {
        tutorialsViewModel.tutorialsViewStateLD.observe(this, {
            handleViewState(it)
        })

    }

    private fun handleViewState(viewState: TutorialsViewState) {
        when (viewState) {
            TutorialsViewState.Loading -> loadingState()
            is TutorialsViewState.onError -> errorState(viewState.error)
            is TutorialsViewState.onSuccess -> successState(viewState.result)
        }
    }

    private fun successState(result: List<TutorialUiModel>) {
        binding.root.visible()
        hideLoading()
        setTutorials(result)
    }

    private fun setTutorials(result: List<TutorialUiModel>) {
        tutorialsAdapter.setData(result)
    }

    private fun errorState(error: Throwable) {
        showRetryDialog(true) {
            tutorialsViewModel.getTutorials()
        }
        hideLoading()
    }

    private fun loadingState() {
        binding.root.gone()
        showLoading()
    }


    private fun onIemOpenClickAction(item: TutorialUiModel) {
        item.localPath?.let {
            when (item.type) {
                TutorialType.PDF -> openPDFIntent(getLocalFileUri(it))
                else -> openVideoIntent(getLocalFileUri(it))
            }
        }
    }

    private fun onIemDownloadClickAction(item: TutorialUiModel) {
        tutorialsViewModel.tutorialSelectedItem = item
        checkPermission(item)
    }

    private fun checkPermission(item: TutorialUiModel) {
        if (isStoragePermissionGranted()) {
            item.url?.let {
                download(it, item.name, item.id)
            }
        } else {
            askForFilePermission()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == FILE_ACCESS_PERMISSION && isStoragePermissionGranted()) {
            retryDownload()
        }
    }

    private fun retryDownload() {
        tutorialsViewModel.tutorialSelectedItem?.let { tutorialUIModel ->
            tutorialUIModel.url?.let {
                download(it, tutorialUIModel.name, tutorialUIModel.id)
            }
        }
    }

    private fun download(downloadUrl: String, name: String?, itemId: Int?) {
        if (downloadUrl.isNotEmpty()) {
            tutorialsViewModel.downloadFile(createFolderAndGetPath(), downloadUrl, name, itemId){
                showProgressDialog()
            }
        }
    }

    private fun showProgressDialog() {
        DownloadProgressDialog.newInstance()
            .show(supportFragmentManager, DownloadProgressDialog::class.simpleName)
    }
}
