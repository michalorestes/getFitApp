package com.jds.fitnessjunkiess.getfitapp.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.WorkoutExerciseAssignment
import com.jds.fitnessjunkiess.getfitapp.data.pojo.WorkoutExercise

@Dao
interface WorkoutExerciseDao {
    @Query("SELECT * FROM WorkoutExerciseAssignment")
    fun selectAll(): LiveData<List<WorkoutExerciseAssignment>>

    @Insert
    fun insert(workout: WorkoutExerciseAssignment)

    @Update
    fun update(workout: WorkoutExerciseAssignment)

    @Delete
    fun delete(workout: WorkoutExerciseAssignment)

    @Query("DELETE FROM WorkoutExerciseAssignment")
    fun deleteAll()

    @Query(
        "SELECT " +
                "e.id AS exerciseId, e.name, e.picture, e.instructions, e.type AS exerciseType, " +
                "e.muscleGroups AS exerciseMuscleGroups, wea.id, wea.userId, wea.workoutId, wea.lengthTime, wea.reps, " +
                "wea.restTime, wea.sprintTime, wea.sets " +
                "FROM WorkoutExerciseAssignment wea " +
                "INNER JOIN exercises e ON wea.exerciseId = e.id " +
                "WHERE wea.workoutId = :workoutId"
    )
    fun getWorkoutExercises(workoutId: Int): LiveData<List<WorkoutExercise>>
}
