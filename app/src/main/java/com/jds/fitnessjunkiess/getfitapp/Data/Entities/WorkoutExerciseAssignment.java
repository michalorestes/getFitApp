package com.jds.fitnessjunkiess.getfitapp.Data.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class WorkoutExerciseAssignment {

  @PrimaryKey(autoGenerate = true)
  private int id;
  private int exerciseId;
  private int workoutId;
  private int userId;
  private String lengthTime;
  private String restTime;
  private String sprintTime;
  private int sets;
  private int reps;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getExerciseId() {
    return exerciseId;
  }

  public void setExerciseId(int exerciseId) {
    this.exerciseId = exerciseId;
  }

  public int getWorkoutId() {
    return workoutId;
  }

  public void setWorkoutId(int workoutId) {
    this.workoutId = workoutId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getLengthTime() {
    return lengthTime;
  }

  public void setLengthTime(String lengthTime) {
    this.lengthTime = lengthTime;
  }

  public String getRestTime() {
    return restTime;
  }

  public void setRestTime(String restTime) {
    this.restTime = restTime;
  }

  public String getSprintTime() {
    return sprintTime;
  }

  public void setSprintTime(String sprintTime) {
    this.sprintTime = sprintTime;
  }

  public int getSets() {
    return sets;
  }

  public void setSets(int sets) {
    this.sets = sets;
  }

  public int getReps() {
    return reps;
  }

  public void setReps(int reps) {
    this.reps = reps;
  }
}
