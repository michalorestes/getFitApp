package com.jds.fitnessjunkiess.getfitapp.data.dataModels

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import android.os.Parcelable
import com.jds.fitnessjunkiess.getfitapp.data.typeConverters.ExercisePropertiesTypeConverter
import com.jds.fitnessjunkiess.getfitapp.data.pojo.MuscleGroupKeys
import kotlinx.android.parcel.Parcelize
import java.util.ArrayList
import java.util.HashMap

@Parcelize
@Entity(tableName = "exercises")
class Exercise(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var userId: Int = 0,
    var name: String = "",
    var picture: String = "",
    var instructions: String? = null,
    var type: String = "",
    var isCustom: Boolean = false,
    @TypeConverters(ExercisePropertiesTypeConverter::class)
    var muscleGroups: MutableMap<String, ArrayList<String>> = hashMapOf(
        Pair(MuscleGroupKeys.PRIMARY, ArrayList()),
        Pair(MuscleGroupKeys.OTHER, arrayListOf(""))
    )
): Parcelable {
    fun getMuscleGroupsByKey(muscleGroupKey: String): List<String>? {
        return this.muscleGroups[muscleGroupKey]
    }

    fun setMuscleGroupsByKey(muscleGroupKey: String, muscleGroup: String) {
        if (muscleGroupKey == MuscleGroupKeys.PRIMARY) {
            this.muscleGroups[muscleGroupKey]!![0] = muscleGroup
        } else {
            val otherMuscleGroups = this.muscleGroups[MuscleGroupKeys.OTHER]
            if (!otherMuscleGroups!!.contains(muscleGroup)) {
                this.muscleGroups[MuscleGroupKeys.OTHER]!!.add(muscleGroup)
            }
        }
    }
}
