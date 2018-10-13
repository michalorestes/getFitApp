package com.jds.fitnessjunkiess.getfitapp.data.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.ExerciseAssignment
import com.jds.fitnessjunkiess.getfitapp.data.pojo.ExerciseRelationship
import com.jds.fitnessjunkiess.getfitapp.data.repositories.ExerciseAssignmentRepository

class ExerciseAssignmentViewModel(application: Application) : AndroidViewModel(application) {

    private val exerciseAssignmentRepository: ExerciseAssignmentRepository =
        ExerciseAssignmentRepository(application)
    private var exerciseRelationsData: LiveData<List<ExerciseRelationship>> = MediatorLiveData()

    fun selectExerciseRelationsData(workoutId: Int): LiveData<List<ExerciseRelationship>> {
        if (this.exerciseRelationsData.value == null) {
            this.exerciseRelationsData =
                    this.exerciseAssignmentRepository.selectRelations(workoutId)
        }

        return exerciseRelationsData
    }

    fun insert(exerciseAssignment: ExerciseAssignment) {
        this.exerciseAssignmentRepository.insert(exerciseAssignment)
    }

    fun batchUpdate(exerciseAssignment: List<ExerciseAssignment>) {
        this.exerciseAssignmentRepository.batchUpdate(exerciseAssignment)
    }

    fun getLastExercisePosition(workoutId: Int): Int {
        return this.exerciseAssignmentRepository.lastExercisePosition(workoutId)
    }

    fun update(exerciseAssignment: ExerciseAssignment) {
        this.exerciseAssignmentRepository.update(exerciseAssignment)
    }
}
