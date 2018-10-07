package com.jds.fitnessjunkiess.getfitapp.data.dataModels

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize
import java.util.ArrayList

@Parcelize
@Entity(tableName = "workouts")
data class Workout(
    @Expose
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @Expose
    var name: String = "",
    @Expose
    var type: String = "",
    @Expose
    @Ignore
    var exercises: List<WorkoutExerciseAssignment> = ArrayList(),
    @Expose
    var userId: Int = 0,
    @Expose
    var schedule: String = ""
) : Parcelable {

    override fun equals(o: Any?): Boolean {
        return o is Workout && this.id == o.id
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + exercises.hashCode()
        result = 31 * result + userId
        result = 31 * result + schedule.hashCode()
        return result
    }
}
