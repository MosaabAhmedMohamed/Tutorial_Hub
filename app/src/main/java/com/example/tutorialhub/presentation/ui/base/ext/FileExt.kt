package com.example.tutorialhub.presentation.ui.base.ext

import android.app.Activity
import java.io.File

fun Activity.getFolderPath(): String {
    val folderPath = (this.getExternalFilesDir(null)?.absolutePath
        .toString() + File.separator + "FolderName" + File.separator)

    if (!File(folderPath).exists()) {
        File(folderPath).mkdir();
    }
    return folderPath
}