package com.jds.fitnessjunkiess.getfitapp.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Update

import com.jds.fitnessjunkiess.getfitapp.data.dataModels.WorkoutExerciseAssignment

@Dao
interface WorkoutExercisesAssignmentDao {
    @Insert
    fun insert(workoutExerciseAssignment: WorkoutExerciseAssignment)

    @Update
    fun update(workout: WorkoutExerciseAssignment)
}
