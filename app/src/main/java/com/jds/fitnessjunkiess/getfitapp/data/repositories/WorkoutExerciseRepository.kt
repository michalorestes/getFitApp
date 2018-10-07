package com.jds.fitnessjunkiess.getfitapp.data.repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.jds.fitnessjunkiess.getfitapp.data.dao.WorkoutExerciseDao
import com.jds.fitnessjunkiess.getfitapp.data.database.WorkoutRoomDatabase
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.WorkoutExerciseAssignment
import com.jds.fitnessjunkiess.getfitapp.data.pojo.WorkoutExercise

class WorkoutExerciseRepository(context: Application, private val workoutId: Int) {
    private val dao: WorkoutExerciseDao
    private val data: LiveData<List<WorkoutExercise>>

    init {
        val database = WorkoutRoomDatabase.getDb(context)
        this.dao = database!!.workoutExerciseDao()
        this.data = dao.getWorkoutExercises(this.workoutId)
    }

    fun getWorkoutExercises(workoutId: Int): LiveData<List<WorkoutExercise>> {
        return data
    }

    fun selectAll(): LiveData<List<WorkoutExerciseAssignment>> {
        return this.dao.selectAll()
    }

    fun insert(workoutExerciseAssignment: WorkoutExerciseAssignment) {
        InsertAsyncTask(this.dao).execute(workoutExerciseAssignment)
    }

    private class InsertAsyncTask internal constructor(private val mAsyncTaskDao: WorkoutExerciseDao) :
        AsyncTask<WorkoutExerciseAssignment, Void, Void>() {

        override fun doInBackground(vararg params: WorkoutExerciseAssignment): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }
}
