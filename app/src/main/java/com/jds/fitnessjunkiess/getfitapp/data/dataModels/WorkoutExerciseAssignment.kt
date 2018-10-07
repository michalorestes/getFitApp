package com.jds.fitnessjunkiess.getfitapp.data.dataModels

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//TODO: Name idea: ExerciseAssignment
@Parcelize
@Entity
class WorkoutExerciseAssignment(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var exerciseId: Int = 0,
    var workoutId: Int = 0,
    var userId: Int = 0,
    var lengthTime: String = "",
    var restTime: String = "",
    var sprintTime: String = "",
    var sets: Int = 0,
    var reps: Int = 0
) : Parcelable
