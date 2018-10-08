package com.jds.fitnessjunkiess.getfitapp.data.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.WorkoutExerciseAssignment
import com.jds.fitnessjunkiess.getfitapp.data.repositories.WorkoutExerciseRepository
import com.jds.fitnessjunkiess.getfitapp.data.pojo.WorkoutExercise

class WorkoutExerciseViewModel(
        private val applicationContext: Application
) : AndroidViewModel(applicationContext) {

    private var repository: WorkoutExerciseRepository? = null
    private lateinit var data: LiveData<List<WorkoutExercise>>

    fun selectAll(workoutId: Int): LiveData<List<WorkoutExercise>> {
        if (this.repository == null) {
            this.repository = WorkoutExerciseRepository(this.applicationContext, workoutId)
            this.data = this.repository!!.getWorkoutExercises(workoutId)
        }

        return this.data
    }

    fun insert(workoutExerciseAssignment: WorkoutExerciseAssignment) {
        this.repository!!.insert(workoutExerciseAssignment)
    }
}
