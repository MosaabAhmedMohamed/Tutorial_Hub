package com.example.core.toturials.data.source.local.dao

import androidx.room.*
import com.example.core.toturials.data.source.local.model.Tutorial
import io.reactivex.Flowable

@Dao
interface TutorialsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTutorials(tutorials: List<Tutorial>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTutorial(tutorials: Tutorial)

    @Query("select * from Tutorial")
    fun getTutorials(): Flowable<List<Tutorial>>

    @Query("select * from Tutorial WHERE id = :tutorialId")
    fun getTutorial(tutorialId:Int): Tutorial

    @Query("delete from Tutorial")
    fun deleteAllEntries()

    @Transaction
    fun cacheTutorials(tutorials:List<Tutorial>) {
        deleteAllEntries()
        insertTutorials(tutorials)
    }

}