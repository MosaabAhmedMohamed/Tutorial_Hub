package com.example.tutorialhub.presentation.ui.tutorials.activity

import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.core.di.ViewModelFactory
import com.example.core.toturials.presentation.uimodel.TutorialUiModel
import com.example.core.toturials.presentation.viewmodel.TutorialsViewModel
import com.example.tutorialhub.databinding.ActivityTutorialsBinding
import com.example.tutorialhub.presentation.ui.base.BaseActivity
import com.example.tutorialhub.presentation.ui.tutorials.adapter.TutorialsAdapter
import java.io.File
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

        tutorialsViewModel.tutorialsLD.observe(this, {
            initTutorialsRV(it)
        })

    }

    private fun initTutorialsRV(list: List<TutorialUiModel>) {
        val adapter = TutorialsAdapter(list){
            onIemClickAction(it)
        }
        binding.rvTutorials.layoutManager =
            GridLayoutManager(this, 2)
        binding.rvTutorials.adapter = adapter
    }

    private fun onIemClickAction(item: TutorialUiModel) {
        item.url?.let {
            down(it,item.name)
        }
    }


    private fun down(downloadUrl: String, name: String?) {


        val mBaseFolderPath = (Environment.getExternalStorageDirectory().toString() + File.separator + "FolderName" + File.separator)

        if (!File(mBaseFolderPath).exists()) {
            File(mBaseFolderPath).mkdir();
        }

        if (downloadUrl.isEmpty()) {

            return;
        }

        val downloadUri = Uri.parse(downloadUrl.trim().trim());
        if (downloadUri == null) {

            return;
        }

        val mFilePath = "file://$mBaseFolderPath/${name}"
        val req = DownloadManager.Request(downloadUri);
        req.setDestinationUri(Uri.parse(mFilePath));
        req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        val dm = this.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val opId = dm.enqueue(req);

        val query = DownloadManager.Query()
        query.setFilterById(opId)

        val c: Cursor = dm.query(query)
        if (c.moveToFirst()) {
            val sizeIndex: Int = c.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES)
            val downloadedIndex: Int =
                c.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR)
            val size: Int = c.getInt(sizeIndex)
            val downloaded: Int = c.getInt(downloadedIndex)
            var progress = 0.0
            if (size != -1) progress = downloaded * 100.0 / size
            Log.d("progressTAG", "down: $progress")
            // At this point you have the progress as a percentage.
        }

        //Check if External Storage permission js allowed
/*        if (!storageAllowed()) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                this,
                Constant.PERMISSIONS_STORAGE,
                Constant.REQUEST_EXTERNAL_STORAGE
            );
            *//* progressDialog.dismiss();
             showToast("Kindly grant the request and try again");*//*
        } else {

            val mBaseFolderPath = (Environment.getExternalStorageDirectory().toString() + File.separator + "FolderName" + File.separator)

            if (!File(mBaseFolderPath).exists()) {
                File(mBaseFolderPath).mkdir();
            }

            if (downloadUrl.isEmpty()) {
               *//* showToast("Download url not found!")*//*
                return;
            }

            val downloadUri = Uri.parse(downloadUrl.trim());
            if (downloadUri == null) {
                *//*showToast("Download url not found!")*//*
                return;
            }

            val mFilePath = "file://$mBaseFolderPath/${"fname"}"
            val req = DownloadManager.Request(downloadUri);
            req.setDestinationUri(Uri.parse(mFilePath));
            req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            val dm = this.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            dm.enqueue(req);
         *//*   progressDialog.dismiss();
            loadInterstitialAd();*//*
        }*/
    }
}
