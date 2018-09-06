package com.jds.fitnessjunkiess.getfitapp.Pojo;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class ExercisesFilter implements Parcelable {

  @Expose
  public List<String> muscleGroup;
  @Expose
  public List<String> types;

  public ExercisesFilter() {
    this.muscleGroup = new ArrayList<>();
    this.types = new ArrayList<>();
  }

  public ExercisesFilter(String muscleGroup, String type) {
    this.muscleGroup = new ArrayList<>();
    this.types = new ArrayList<>();

    if (!muscleGroup.equals("")) {
      this.muscleGroup.add(muscleGroup);
    }

    if (!type.equals("")) {
      this.types.add(type);
    }
  }

  public ExercisesFilter(Parcel in) {
    this.muscleGroup = new ArrayList<>();
    this.types = new ArrayList<>();
    in.readList(this.muscleGroup, String.class.getClassLoader());
    in.readList(this.types, String.class.getClassLoader());
  }

  public String muscleGroupsToString() {
    StringBuilder string = new StringBuilder();
    for (String muscleGroup : this.muscleGroup) {
      string.append(muscleGroup);
    }

    return string.toString();
  }

  public String exerciseTypesToString() {
    StringBuilder string = new StringBuilder();
    for (String type: this.types) {
      string.append(type);
    }

    return string.toString();
  }

  @Override
  public int describeContents() {
    return Parcelable.CONTENTS_FILE_DESCRIPTOR;
  }

  public static final Parcelable.Creator CREATOR =
      new Parcelable.Creator() {
        public ExercisesFilter createFromParcel(Parcel in) {
          return new ExercisesFilter(in);
        }

        public ExercisesFilter[] newArray(int size) {
          return new ExercisesFilter[size];
        }
      };

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeList(this.muscleGroup);
    dest.writeList(this.types);
  }
}
