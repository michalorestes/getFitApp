package com.jds.fitnessjunkiess.getfitapp.data.typeConverters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

class ExercisePropertiesTypeConverter {
    @TypeConverter
    fun jsonToMap(json: String): MutableMap<String, ArrayList<String>>? {
        val type = object : TypeToken<Map<String, ArrayList<String>>>() {

        }.type
        val gson = Gson()

        return gson.fromJson<MutableMap<String, ArrayList<String>>>(json, type)
    }

    @TypeConverter
    fun mapToJson(muscleGroups: MutableMap<String, ArrayList<String>>): String {
        val gson = Gson()

        return gson.toJson(muscleGroups)
    }
}
