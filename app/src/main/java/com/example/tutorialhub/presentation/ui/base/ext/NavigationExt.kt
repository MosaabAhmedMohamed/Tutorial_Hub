package com.example.tutorialhub.presentation.ui.base.ext

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tutorialhub.presentation.ui.tutorials.ui.activity.TutorialsActivity


fun Fragment.navigate(action: Int, args: Bundle? = null) {
    findNavController().navigate(action, args)
}

fun Activity.navigateToTutorials(finishCurrent: Boolean = true) {
    startActivity(Intent(this, TutorialsActivity::class.java))
    if (finishCurrent)
        finish()
}
