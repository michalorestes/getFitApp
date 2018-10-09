package com.jds.fitnessjunkiess.getfitapp.data.pojo

import android.arch.persistence.room.Embedded
import android.os.Parcelable
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Exercise
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.ExerciseAssignment
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ExerciseRelationship(
    @Embedded(prefix = "exercise_")
    val exercise: Exercise,
    @Embedded
    val relationship: ExerciseAssignment
): Parcelable
