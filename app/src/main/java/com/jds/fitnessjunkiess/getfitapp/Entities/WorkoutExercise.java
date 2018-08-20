package com.jds.fitnessjunkiess.getfitapp.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class WorkoutExercise extends Exercise implements Parcelable {
  public static final Parcelable.Creator CREATOR =
      new Parcelable.Creator() {
        public WorkoutExercise createFromParcel(Parcel in) {
          return new WorkoutExercise(in);
        }

        public WorkoutExercise[] newArray(int size) {
          return new WorkoutExercise[size];
        }
      };

  @Expose
  private int sets;
  @Expose
  private int reps;
  @Expose
  private int exerciseAssignmentId;

  public WorkoutExercise(Parcel in) {
    this.exerciseAssignmentId = in.readInt();
    this.sets = in.readInt();
    this.reps = in.readInt();
  }

  public int getExerciseAssignmentId() {
    return exerciseAssignmentId;
  }

  public void setExerciseAssignmentId(int exerciseAssignmentId) {
    this.exerciseAssignmentId = exerciseAssignmentId;
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

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.exerciseAssignmentId);
    dest.writeInt(this.sets);
    dest.writeInt(this.reps);
  }
}
