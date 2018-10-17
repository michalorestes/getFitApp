package com.jds.fitnessjunkiess.getfitapp.data.dataModels

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.jds.fitnessjunkiess.getfitapp.data.pojo.LogSet
import com.jds.fitnessjunkiess.getfitapp.data.pojo.LogTime
import java.util.*

@Entity(tableName = "exercise_log")
data class ExerciseLog(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val exerciseId: Int = 0,
    val date: String,
    val setLogs: List<LogSet>?,
    val timeLogs: LogTime?
)