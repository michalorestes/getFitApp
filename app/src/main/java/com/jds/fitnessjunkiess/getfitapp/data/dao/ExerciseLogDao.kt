package com.jds.fitnessjunkiess.getfitapp.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.ExerciseLog

@Dao
interface ExerciseLogDao {
    @Insert
    fun insert(exerciseLog: ExerciseLog)

    @Query("SELECT * FROM exercise_log WHERE id = :logId")
    fun selectOne(logId: Int): LiveData<ExerciseLog>
}