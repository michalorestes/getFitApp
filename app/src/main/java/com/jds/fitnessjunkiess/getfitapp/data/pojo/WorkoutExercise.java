package com.jds.fitnessjunkiess.getfitapp.data.pojo;

import android.arch.persistence.room.ColumnInfo;

public class WorkoutExercise {
  @ColumnInfo(name = "id")
  public  int id;
  @ColumnInfo(name = "name")
  public  String exerciseName;
  @ColumnInfo(name = "picture")
  public  String picture;
  @ColumnInfo(name = "instructions")
  public  String instructions;
  @ColumnInfo(name = "exerciseType")
  public  String exerciseType;
  @ColumnInfo(name = "exerciseMuscleGroups")
  public  String exerciseMuscleGroups;
  @ColumnInfo(name = "exerciseId")
  public  int exerciseId;
  @ColumnInfo(name = "workoutId")
  public  int workoutId;
  @ColumnInfo(name = "userId")
  public  int userId;
  @ColumnInfo(name = "lengthTime")
  public  String lengthTime;
  @ColumnInfo(name = "restTime")
  public  String restTime;
  @ColumnInfo(name = "sprintTime")
  public  String sprintTime;
  @ColumnInfo(name = "sets")
  public  int sets;
  @ColumnInfo(name = "reps")
  public  int reps;
}
