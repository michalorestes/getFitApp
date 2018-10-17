package com.jds.fitnessjunkiess.getfitapp.data.repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.persistence.db.SimpleSQLiteQuery
import android.os.AsyncTask
import android.util.Log

import com.jds.fitnessjunkiess.getfitapp.data.dao.ExerciseDao
import com.jds.fitnessjunkiess.getfitapp.data.database.WorkoutRoomDatabase
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Exercise
import com.jds.fitnessjunkiess.getfitapp.data.pojo.ExercisesFilters

class ExercisesRepository(context: Application) {
    private val dao: ExerciseDao

    init {
        val database = WorkoutRoomDatabase.getDb(context)
        this.dao = database!!.exerciseDao()
    }

    fun filterSelect(exercisesFilter: ExercisesFilters): LiveData<List<Exercise>> {
        val query = this.buildFilterQuery(exercisesFilter)
        Log.d("------>", query)
        val simpleSQLiteQuery = SimpleSQLiteQuery(query)

        return this.dao.filterSelect(simpleSQLiteQuery)
    }

    fun select(exerciseId: Int): LiveData<Exercise> {
        return this.dao.select(exerciseId)
    }

    fun insert(exercise: Exercise) {
        InsertAsyncTask(this.dao).execute(exercise)
    }

    private fun buildFilterQuery(exercisesFilter: ExercisesFilters): String {
        if (exercisesFilter.types.size == 0) exercisesFilter.types.add("")
        if (exercisesFilter.muscleGroups.size == 0) exercisesFilter.muscleGroups.add("")

        val subQuery = StringBuilder()
        subQuery.append("SELECT * FROM exercises WHERE ")
        subQuery.append("isCustom = ").append(if (exercisesFilter.isCustom) 1 else 0)
            .append(" AND ")

        for (i in 0 until exercisesFilter.muscleGroups.size) {
            if (i != 0) {
                subQuery.append("OR ")
            }

            subQuery
                .append("muscleGroups LIKE '%")
                .append(exercisesFilter.muscleGroups[i])
                .append("%' ")
        }

        val query = StringBuilder()
        query.append("SELECT * FROM (").append(subQuery)
        query.append(") WHERE ")

        for (i in 0 until exercisesFilter.types.size) {
            if (i != 0) query.append("OR ")
            query.append("type LIKE '%").append(exercisesFilter.types[i]).append("%' ")
        }

        return query.toString()
    }

    private class InsertAsyncTask internal constructor(private val exerciseDao: ExerciseDao) :
        AsyncTask<Exercise, Void, Void>() {

        override fun doInBackground(vararg params: Exercise): Void? {
            exerciseDao.insert(params[0])
            return null
        }
    }
}
