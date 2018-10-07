package com.jds.fitnessjunkiess.getfitapp.data.repositories

import android.app.Application
import android.os.AsyncTask

import com.jds.fitnessjunkiess.getfitapp.data.dao.WorkoutExercisesAssignmentDao
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.WorkoutExerciseAssignment
import com.jds.fitnessjunkiess.getfitapp.data.database.WorkoutRoomDatabase

class WorkoutExerciseAssignmentRepository(application: Application) {

    private val workoutExercisesAssignmentDao: WorkoutExercisesAssignmentDao

    init {
        val workoutRoomDatabase = WorkoutRoomDatabase.getDb(application)
        this.workoutExercisesAssignmentDao = workoutRoomDatabase!!.workoutExercisesAssignmentDao()
    }

    fun insert(workoutExerciseAssignment: WorkoutExerciseAssignment) {
        InsertAsyncTask(this.workoutExercisesAssignmentDao).execute(workoutExerciseAssignment)
    }

    fun update(workoutExerciseAssignment: WorkoutExerciseAssignment) {
        UpdateAsyncTask(this.workoutExercisesAssignmentDao).execute(workoutExerciseAssignment)
    }

    private class InsertAsyncTask internal constructor(private val mAsyncTaskDao: WorkoutExercisesAssignmentDao) :
        AsyncTask<WorkoutExerciseAssignment, Void, Void>() {

        override fun doInBackground(vararg params: WorkoutExerciseAssignment): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }

    private class UpdateAsyncTask internal constructor(private val mAsyncTaskDao: WorkoutExercisesAssignmentDao) :
        AsyncTask<WorkoutExerciseAssignment, Void, Void>() {

        override fun doInBackground(vararg params: WorkoutExerciseAssignment): Void? {
            mAsyncTaskDao.update(params[0])
            return null
        }
    }
}
