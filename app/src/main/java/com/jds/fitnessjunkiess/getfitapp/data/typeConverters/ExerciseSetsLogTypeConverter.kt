package com.jds.fitnessjunkiess.getfitapp.data.typeConverters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jds.fitnessjunkiess.getfitapp.data.pojo.LogSet
import java.util.ArrayList

class ExerciseSetsLogTypeConverter {
    @TypeConverter
    fun jsonToMap(json: String): List<LogSet>? {
        val type = object : TypeToken<List<LogSet>>() {

        }.type
        val gson = Gson()

        return gson.fromJson<List<LogSet>>(json, type)
    }

    @TypeConverter
    fun mapToJson(muscleGroups: List<LogSet>): String {
        val gson = Gson()

        return gson.toJson(muscleGroups)
    }
}
