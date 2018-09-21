package com.jds.fitnessjunkiess.getfitapp.data.dataModels;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "workouts")
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

  @Expose
  @PrimaryKey(autoGenerate = true)
  private int id;
  @Expose
  private String name;
  @Expose
  private String type;
  @Expose
  @Ignore
  private List<WorkoutExerciseAssignment> exercises;
  @Expose
  private Integer userId;
  @Expose
  private String schedule;

  public Workout() {
    this.exercises = new ArrayList<>();
  }

  public Workout(Parcel in) {
    this.exercises = new ArrayList<>();
    this.userId = in.readInt();
    this.id = in.readInt();
    this.name = in.readString();
    this.type = in.readString();
    this.schedule = in.readString();

    in.readList(this.exercises, WorkoutExerciseAssignment.class.getClassLoader());
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSchedule() {
    return schedule;
  }

  public void setSchedule(String schedule) {
    this.schedule = schedule;
  }

  public List<WorkoutExerciseAssignment> getExercises() {
    return exercises;
  }

  public void setExercises(List<WorkoutExerciseAssignment> exercises) {
    this.exercises = exercises;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getUserId() {
    return userId;
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof Workout && this.getId() == (((Workout) o).getId());
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
    dest.writeString(this.type);
    dest.writeString(this.schedule);
    dest.writeList(this.exercises);
  }

  @Override
  public String toString() {
    return "\nName: " + this.getName() + "\n ID: " + this.getId() + "\n" + this.schedule +
        "\n" + this.type;
  }
}