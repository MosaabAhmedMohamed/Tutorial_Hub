package com.example.tutorialhub.presentation.ui.base.ext

import android.app.Activity
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

fun Activity.createFolderAndGetPath(): String {
    val folderPath = (this.getExternalFilesDir(null)?.absolutePath
        .toString() + File.separator + "Tutorials" + File.separator)

    if (!File(folderPath).exists()) {
        File(folderPath).mkdir();
    }
    return folderPath
}

fun Activity.getLocalFileUri(localPath: String): Uri {
    val file = File(localPath)
    return FileProvider.getUriForFile(this, "${this.packageName}.provider", file)
}