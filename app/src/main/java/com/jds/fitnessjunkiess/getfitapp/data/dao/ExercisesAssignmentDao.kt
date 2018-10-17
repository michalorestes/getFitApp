package com.jds.fitnessjunkiess.getfitapp.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

import com.jds.fitnessjunkiess.getfitapp.data.dataModels.ExerciseAssignment
import com.jds.fitnessjunkiess.getfitapp.data.pojo.ExerciseRelationship

@Dao
interface ExercisesAssignmentDao {
    @Insert
    fun insert(workoutExerciseAssignment: ExerciseAssignment)

    @Update
    fun update(workout: ExerciseAssignment)

    @Query("DELETE FROM exercise_assignment")
    fun deleteAll()

    @Delete
    fun delete(relationship: ExerciseAssignment)

    @Query("SELECT * FROM exercise_assignment ORDER BY position ASC")
    fun selectAll(): LiveData<List<ExerciseAssignment>>

    @Update
    fun batchUpdate(vararg assignment: ExerciseAssignment)

    @Query("SELECT position FROM exercise_assignment WHERE workoutId = :workoutId ORDER BY position DESC LIMIT 1")
    fun lastExercisePosition(workoutId: Int): Int

    @Query(
        "SELECT " +
                "ea.*," +
                "e.id AS exercise_id," +
                "e.userId AS exercise_userId," +
                "e.name AS exercise_name," +
                "e.picture AS exercise_picture," +
                "e.instructions AS exercise_instructions," +
                "e.type AS exercise_type," +
                "e.isCustom AS exercise_isCustom," +
                "e.muscleGroups AS exercise_muscleGroups, " +
                "e.defaultLoggingType AS exercise_defaultLoggingType " +
                "FROM exercise_assignment ea " +
                "INNER JOIN exercises e ON ea.exerciseId = e.id " +
                "WHERE ea.workoutId = :workoutId  ORDER BY ea.position ASC;"
    )
    fun selectRelations(workoutId: Int): LiveData<List<ExerciseRelationship>>
}
