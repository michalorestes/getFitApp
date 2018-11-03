package com.jds.fitnessjunkiess.getfitapp.data.dataModels

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.jds.fitnessjunkiess.getfitapp.data.pojo.LogSet
import com.jds.fitnessjunkiess.getfitapp.data.pojo.LogTime
import java.util.*

@Entity(tableName = "exercise_log")
data class ExerciseLog(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var exerciseId: Int = 0,
    var workoutId: Int = 0,
    var date: String? = "",
    var setLogs: List<LogSet>? = null,
    var timeLogs: LogTime? = LogTime()
)