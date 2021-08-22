package com.example.core.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.toturials.data.source.local.dao.TutorialsDao
import com.example.core.toturials.data.source.local.model.Tutorial


@Database(
    entities = [Tutorial::class],
    version = TutorialsHub_DATABASE_VERSION_NUMBER
)

abstract class TutorialsHubDatabase : RoomDatabase() {

    abstract fun tutorialsDao(): TutorialsDao

}
const val TutorialsHub_DATABASE_VERSION_NUMBER = 4
