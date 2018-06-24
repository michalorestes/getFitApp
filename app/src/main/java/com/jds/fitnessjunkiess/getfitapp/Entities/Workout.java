package com.jds.fitnessjunkiess.getfitapp.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Workout implements Parcelable {
  public static final Parcelable.Creator CREATOR =
      new Parcelable.Creator() {
        public Workout createFromParcel(Parcel in) {
          return new Workout(in);
        }

        public Workout[] newArray(int size) {
          return new Workout[size];
        }
      };

  @Expose private Integer id;
  @Expose private String name;
  @Expose private List<WorkoutExercise> exercises;
  @Expose private Integer userId;

  public Workout() {
    this.exercises = new ArrayList<>();
  }

  public Workout(Parcel in) {
    this.exercises = new ArrayList<>();
    this.userId = in.readInt();
    this.id = in.readInt();
    this.name = in.readString();
    in.readList(this.exercises, WorkoutExercise.class.getClassLoader());
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<WorkoutExercise> getExercises() {
    return exercises;
  }

  public void setExercises(List<WorkoutExercise> exercises) {
    this.exercises = exercises;
  }

  public Exercise findExercise(Integer exerciseId) {
    for (Exercise e : this.getExercises()) {
      if (e.getId() == exerciseId) {
        return e;
      }
    }

    return null;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Workout) {
      return (this.getId()).equals(((Workout) o).getId());
    }

    return false;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.userId);
    dest.writeInt(this.id);
    dest.writeString(this.name);
    dest.writeList(this.exercises);
  }

  @Override
  public String toString() {
    return "\nName: " + this.getName() + "\n ID: " + this.getId();
  }
}
