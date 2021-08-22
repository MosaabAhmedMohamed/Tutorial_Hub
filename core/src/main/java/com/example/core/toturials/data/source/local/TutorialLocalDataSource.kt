package com.example.core.toturials.data.source.local

import android.content.Context
import com.example.core.toturials.data.source.local.dao.TutorialsDao
import com.example.core.toturials.data.source.local.model.Tutorial
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Flowable
import io.reactivex.Observable
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.Type
import java.nio.charset.Charset
import javax.inject.Inject

class TutorialLocalDataSource @Inject constructor(
    private val context: Context,
    private val tutorialsDao: TutorialsDao,
    private val gson: Gson
) {


    fun getTutorials(): Flowable<List<Tutorial>> {
        return tutorialsDao.getTutorials()
            .flatMap {
                if (it.isEmpty()) {
                    handleLoadingFromFileAndCaching()
                }
                Flowable.just(it)
            }
    }

    private fun handleLoadingFromFileAndCaching() {
        handleJsonString().flatMap {
            cacheTutorials(mapJsonStringToListOfTutorial(it))
            Observable.just(true)
        }.subscribe()
    }

    private fun cacheTutorials(tutorials: List<Tutorial>) = tutorialsDao.cacheTutorials(tutorials)

    private fun loadFromFile(): ByteArray {
        val inputStream: InputStream = context.assets.open("getListOfFilesResponse.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        return buffer
    }

    private fun handleJsonString(): Observable<String> {
        return try {
            val buffer = loadFromFile()
            val jsonStr = String(buffer, Charset.forName("UTF-8"))
            Observable.just(jsonStr)
        } catch (e: IOException) {
            Observable.error(e)
        }
    }

    private fun mapJsonStringToListOfTutorial(jsonString: String): List<Tutorial> {
        val listUserType: Type = object : TypeToken<List<Tutorial?>>() {}.type
        return gson.fromJson(jsonString, listUserType)
    }
}