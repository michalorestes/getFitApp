package com.jds.fitnessjunkiess.getfitapp.data.typeConverters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jds.fitnessjunkiess.getfitapp.data.pojo.LogSet
import com.jds.fitnessjunkiess.getfitapp.data.pojo.LogTime
import java.util.ArrayList

class ExerciseTimeLogTypeConverter {
    @TypeConverter
    fun jsonToMap(json: String): LogTime? {
        val type = object : TypeToken<LogTime>() {

        }.type
        val gson = Gson()

        return gson.fromJson<LogTime>(json, type)
    }

    @TypeConverter
    fun mapToJson(muscleGroups: LogTime): String {
        val gson = Gson()

        return gson.toJson(muscleGroups)
    }
}
