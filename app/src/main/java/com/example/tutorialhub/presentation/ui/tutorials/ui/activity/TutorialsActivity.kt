package com.example.tutorialhub.presentation.ui.tutorials.ui.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.core.di.ViewModelFactory
import com.example.core.toturials.presentation.uimodel.TutorialUiModel
import com.example.core.toturials.presentation.viewmodel.TutorialsViewModel
import com.example.tutorialhub.databinding.ActivityTutorialsBinding
import com.example.tutorialhub.presentation.ui.base.BaseActivity
import com.example.tutorialhub.presentation.ui.base.ext.FILE_ACCESS_PERMISSION
import com.example.tutorialhub.presentation.ui.base.ext.askForFilePermission
import com.example.tutorialhub.presentation.ui.base.ext.getFolderPath
import com.example.tutorialhub.presentation.ui.base.ext.isStoragePermissionGranted
import com.example.tutorialhub.presentation.ui.tutorials.ui.adapter.TutorialsAdapter
import javax.inject.Inject
import android.app.AlertDialog
import com.example.tutorialhub.R
import com.example.tutorialhub.presentation.ui.base.DownloadProgressDialog

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

        tutorialsViewModel.tutorialsLD.observe(this, {
            initTutorialsRV(it)
        })

    }

    private fun initTutorialsRV(list: List<TutorialUiModel>) {
        val adapter = TutorialsAdapter(list) {
            onIemClickAction(it)
        }
        binding.rvTutorials.layoutManager =
            GridLayoutManager(this, 2)
        binding.rvTutorials.adapter = adapter
    }

    private fun onIemClickAction(item: TutorialUiModel) {
        tutorialsViewModel.tutorialSelectedItem = item
        checkPermission(item)
    }

    private fun checkPermission(item: TutorialUiModel) {
        if (isStoragePermissionGranted()) {
            item.url?.let {
                down(it, item.name, item.id)
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
                down(it, tutorialUIModel.name, tutorialUIModel.id)
            }
        }
    }

    private fun down(downloadUrl: String, name: String?, itemId: Int?) {
        if (downloadUrl.isNotEmpty()) {
            tutorialsViewModel.downloadFile(getFolderPath(), downloadUrl, name, itemId)
            showProgressDialog()
        }
    }

    private fun showProgressDialog() {
        DownloadProgressDialog.newInstance()
            .show(supportFragmentManager, DownloadProgressDialog::class.simpleName)
    }
}
