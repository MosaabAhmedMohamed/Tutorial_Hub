package com.example.core.toturials.data.source.local

import android.content.Context
import com.example.core.toturials.data.source.local.model.Tutorial
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.Type
import java.nio.charset.Charset
import javax.inject.Inject

class TutorialLocalDataSource @Inject constructor(private val context: Context) {

    fun getTutorials(): Observable<List<Tutorial>> {
        return Observable.create {
            val jsonString = handleJsonString(it)
            jsonString?.let {jsonStr->
                it.onNext(mapJsonStringToListOfTutorial(jsonStr))
            }
            it.onComplete()
        }
    }



    private fun loadFromFile(): ByteArray {
        val inputStream: InputStream = context.assets.open("getListOfFilesResponse.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        return buffer
    }

    private fun handleJsonString(it: ObservableEmitter<List<Tutorial>>) =
        try {
            val buffer = loadFromFile()
            String(buffer, Charset.forName("UTF-8"))
        } catch (e: IOException) {
            it.onError(e.cause!!)
            null
        }

    private fun mapJsonStringToListOfTutorial(jsonString: String): List<Tutorial> {
        val listUserType: Type = object : TypeToken<List<Tutorial?>>() {}.type
        return Gson().fromJson(jsonString, listUserType)
    }
}