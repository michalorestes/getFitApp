package com.jds.fitnessjunkiess.getfitapp.data.repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.jds.fitnessjunkiess.getfitapp.data.dao.ExerciseLogDao
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.ExerciseLog
import com.jds.fitnessjunkiess.getfitapp.data.database.WorkoutRoomDatabase

class ExerciseLogRepository(context: Application) {

    private val dao: ExerciseLogDao = WorkoutRoomDatabase.getDb(context)!!.exerciseLogDao()

    fun selectOne(logId: Int): LiveData<ExerciseLog> {
        return this.dao.selectOne(logId)
    }

}