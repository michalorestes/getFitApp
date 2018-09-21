package com.jds.fitnessjunkiess.getfitapp.data.dataModels;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.jds.fitnessjunkiess.getfitapp.data.typeConverters.ExercisePropertiesTypeConverter;
import com.jds.fitnessjunkiess.getfitapp.data.pojo.MuscleGroupKeys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity(tableName = "exercises")
public class Exercise {

  @PrimaryKey(autoGenerate = true)
  private int id;
  private int userId;
  private String name;
  private String picture;
  private String instructions;
  private String type = "";

  @TypeConverters(ExercisePropertiesTypeConverter.class)
  private Map<String, List<String>> muscleGroups;

  public Exercise() {
    this.muscleGroups = new HashMap<>();
    this.muscleGroups.put(MuscleGroupKeys.PRIMARY, new ArrayList<String>());
    this.muscleGroups.put(MuscleGroupKeys.OTHER, new ArrayList<String>());
    this.muscleGroups.get(MuscleGroupKeys.PRIMARY).add("");
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPicture() {
    return picture;
  }

  public String getInstructions() {
    return instructions;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public Map<String, List<String>> getMuscleGroups() {
    return this.muscleGroups;
  }

  public void setMuscleGroups(Map<String, List<String>> muscleGroups) {
    this.muscleGroups = muscleGroups;
  }

  public List<String> getMuscleGroupsByKey(String muscleGroupKey) {
    return this.muscleGroups.get(muscleGroupKey);
  }

  public void setMuscleGroupsByKey(String muscleGroupKey, String muscleGroup) {
    if (muscleGroupKey.equals(MuscleGroupKeys.PRIMARY)) {
      this.muscleGroups.get(muscleGroupKey).set(0, muscleGroup);
    } else {
      List<String> otherMuscleGroups = this.muscleGroups.get(MuscleGroupKeys.OTHER);
      if (!otherMuscleGroups.contains(muscleGroup)) {
        this.muscleGroups.get(MuscleGroupKeys.OTHER).add(muscleGroup);
      }
    }
  }

  @Override
  public String toString() {
    return "\nID: "
        + getId()
        + " "
        + "Name: "
        + getName()
        + " "
        + "Picture URL: "
        + getPicture()
        + " "
        + "Instructions: "
        + getInstructions()
        + " "
        + "Type: "
        + getType();
  }
}
