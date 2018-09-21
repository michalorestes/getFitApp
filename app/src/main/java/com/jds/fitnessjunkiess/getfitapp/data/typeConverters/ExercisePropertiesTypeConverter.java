package com.jds.fitnessjunkiess.getfitapp.data.typeConverters;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class ExercisePropertiesTypeConverter {

  @TypeConverter
  public static Map<String, List<String>> jsonToMap(String json) {
    Type type = new TypeToken<Map<String, List<String>>>() {}.getType();
    Gson gson = new Gson();

    return gson.fromJson(json, type);
  }

  @TypeConverter
  public static String mapToJson(Map<String, List<String>> muscleGroups) {
    Gson gson = new Gson();

    return gson.toJson(muscleGroups);
  }
}
