package com.jds.fitnessjunkiess.getfitapp.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(
  tableName = "workout_exercises"
//  foreignKeys =
//    @ForeignKey(
//        entity = Workout.class,
//        parentColumns = "id",
//        childColumns = "workoutId",
//        onDelete = CASCADE
//    )
)
public class WorkoutExercise {

  @PrimaryKey(autoGenerate = true)
  private int id;
  private int exerciseId;
  private int workoutId;
  private String length;
  private String rest;
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

  public String getLength() {
    return length;
  }

  public void setLength(String length) {
    this.length = length;
  }

  public String getRest() {
    return rest;
  }

  public void setRest(String rest) {
    this.rest = rest;
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
