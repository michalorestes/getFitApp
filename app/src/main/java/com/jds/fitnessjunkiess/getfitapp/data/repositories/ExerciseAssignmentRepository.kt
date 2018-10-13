package com.jds.fitnessjunkiess.getfitapp.data.repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

import com.jds.fitnessjunkiess.getfitapp.data.dao.ExercisesAssignmentDao
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.ExerciseAssignment
import com.jds.fitnessjunkiess.getfitapp.data.database.WorkoutRoomDatabase
import com.jds.fitnessjunkiess.getfitapp.data.pojo.ExerciseRelationship

class ExerciseAssignmentRepository(application: Application) {

    private val dao: ExercisesAssignmentDao

    init {
        val workoutRoomDatabase = WorkoutRoomDatabase.getDb(application)
        this.dao = workoutRoomDatabase!!.exercisesAssignmentDao()
    }

    fun insert(workoutExerciseAssignment: ExerciseAssignment) {
        InsertAsyncTask(this.dao).execute(workoutExerciseAssignment)
    }

    fun update(workoutExerciseAssignment: ExerciseAssignment) {
        UpdateAsyncTask(this.dao).execute(workoutExerciseAssignment)
    }

    fun batchUpdate(exerciseAssignment: List<ExerciseAssignment>) {
        BatchUpdateAsyncTask(this.dao).execute(*exerciseAssignment.toTypedArray())
    }

    fun selectRelations(workoutId: Int): LiveData<List<ExerciseRelationship>> {
        return this.dao.selectRelations(workoutId)
    }

    fun lastExercisePosition(workoutId: Int): Int {
        return this.dao.lastExercisePosition(workoutId)
    }

    private class InsertAsyncTask internal constructor(private val mAsyncTaskDao: ExercisesAssignmentDao) :
        AsyncTask<ExerciseAssignment, Void, Void>() {

        override fun doInBackground(vararg params: ExerciseAssignment): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }

    private class UpdateAsyncTask internal constructor(private val mAsyncTaskDao: ExercisesAssignmentDao) :
        AsyncTask<ExerciseAssignment, Void, Void>() {

        override fun doInBackground(vararg params: ExerciseAssignment): Void? {
            mAsyncTaskDao.update(params[0])
            return null
        }
    }

    private class BatchUpdateAsyncTask internal constructor(private val mAsyncTaskDao: ExercisesAssignmentDao) :
        AsyncTask<ExerciseAssignment, Void, Void>() {

        override fun doInBackground(vararg params: ExerciseAssignment): Void? {
            mAsyncTaskDao.batchUpdate(*params)
            return null
        }
    }
}
