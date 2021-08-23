package com.example.tutorialhub.presentation.ui.base.ext

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


fun Activity.isStoragePermissionGranted(): Boolean {
    val read_permission =
        ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
    val write_permission =
        ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    return read_permission == PackageManager.PERMISSION_GRANTED && write_permission == PackageManager.PERMISSION_GRANTED

}


fun Activity.askForFilePermission() {
    ActivityCompat.requestPermissions(
        this, arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ),
        FILE_ACCESS_PERMISSION
    )
}

const val FILE_ACCESS_PERMISSION = 2222