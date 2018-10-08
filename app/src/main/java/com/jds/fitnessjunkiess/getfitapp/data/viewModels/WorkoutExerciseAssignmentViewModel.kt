package com.jds.fitnessjunkiess.getfitapp.data.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.WorkoutExerciseAssignment
import com.jds.fitnessjunkiess.getfitapp.data.repositories.WorkoutExerciseAssignmentRepository

class WorkoutExerciseAssignmentViewModel(application: Application) : AndroidViewModel(application) {

    private val workoutExerciseAssignmentRepository: WorkoutExerciseAssignmentRepository = WorkoutExerciseAssignmentRepository(application)

    fun insert(workoutExerciseAssignment: WorkoutExerciseAssignment) {
        this.workoutExerciseAssignmentRepository.insert(workoutExerciseAssignment)
    }

    fun update(workoutExerciseAssignment: WorkoutExerciseAssignment) {
        this.workoutExerciseAssignmentRepository.update(workoutExerciseAssignment)
    }
}
