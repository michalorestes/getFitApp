package com.jds.fitnessjunkiess.getfitapp.data.repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.jds.fitnessjunkiess.getfitapp.data.dao.WorkoutDao
import com.jds.fitnessjunkiess.getfitapp.data.database.WorkoutRoomDatabase
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Workout

class WorkoutsRepository(context: Application) {
    private val workoutDao: WorkoutDao
    val data: LiveData<List<Workout>>

    init {
        val workoutDatabase = WorkoutRoomDatabase.getDb(context)
        this.workoutDao = workoutDatabase!!.workoutDao()
        this.data = this.workoutDao.selectAll()
    }

    fun insert(workout: Workout) {
        InsertAsyncTask(workoutDao).execute(workout)
    }

    private class InsertAsyncTask internal constructor(private val mAsyncTaskDao: WorkoutDao) :
        AsyncTask<Workout, Void, Void>() {

        override fun doInBackground(vararg params: Workout): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }
}
