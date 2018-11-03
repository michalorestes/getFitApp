package com.jds.fitnessjunkiess.getfitapp.data.repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import com.jds.fitnessjunkiess.getfitapp.data.dao.ExerciseLogDao
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.ExerciseLog
import com.jds.fitnessjunkiess.getfitapp.data.database.WorkoutRoomDatabase
import kotlinx.coroutines.experimental.launch

class ExerciseLogRepository(context: Application) {

    private val dao: ExerciseLogDao = WorkoutRoomDatabase.getDb(context)!!.exerciseLogDao()

    fun selectOne(exerciseId: Int, workoutId: Int): LiveData<ExerciseLog> {
        return this.dao.selectOne(exerciseId, workoutId)
    }

    fun insertLog(log: ExerciseLog) {
        launch { dao.insert(log) }
    }

}