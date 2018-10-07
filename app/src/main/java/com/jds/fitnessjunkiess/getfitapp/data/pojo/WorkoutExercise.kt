package com.jds.fitnessjunkiess.getfitapp.data.pojo

import android.arch.persistence.room.ColumnInfo

data class WorkoutExercise(
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "name")
    var exerciseName: String = "",
    @ColumnInfo(name = "picture")
    var picture: String = "",
    @ColumnInfo(name = "instructions")
    var instructions: String = "",
    @ColumnInfo(name = "exerciseType")
    var exerciseType: String = "",
    @ColumnInfo(name = "exerciseMuscleGroups")
    var exerciseMuscleGroups: String = "",
    @ColumnInfo(name = "exerciseId")
    var exerciseId: Int = 0,
    @ColumnInfo(name = "workoutId")
    var workoutId: Int = 0,
    @ColumnInfo(name = "userId")
    var userId: Int = 0,
    @ColumnInfo(name = "lengthTime")
    var lengthTime: String = "",
    @ColumnInfo(name = "restTime")
    var restTime: String = "",
    @ColumnInfo(name = "sprintTime")
    var sprintTime: String = "",
    @ColumnInfo(name = "sets")
    var sets: Int = 0,
    @ColumnInfo(name = "reps")
    var reps: Int = 0
)
