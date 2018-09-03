package com.jds.fitnessjunkiess.getfitapp.Pojo;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.jds.fitnessjunkiess.getfitapp.Data.DataModels.Exercise;
import com.jds.fitnessjunkiess.getfitapp.Dialogs.ExerciseFilterDialog;

public class ExercisesFilter implements Parcelable {
  public static final Parcelable.Creator CREATOR =
      new Parcelable.Creator() {
        public ExercisesFilter createFromParcel(Parcel in) {
          return new ExercisesFilter(in);
        }

        public ExercisesFilter[] newArray(int size) {
          return new ExercisesFilter[size];
        }
      };

  public ExercisesFilter() {
    this.muscleGroup = "";
    this.type = "";
  }

  public ExercisesFilter(String muscleGroup, String type) {
    this.muscleGroup = muscleGroup;
    this.type = type;
  }

  public ExercisesFilter(Parcel in) {
    this.muscleGroup = in.readString();
    this.type = in.readString();
  }

  @Expose
  public String muscleGroup;
  @Expose
  public String type;

  @Override
  public int describeContents() {
    return Parcelable.CONTENTS_FILE_DESCRIPTOR;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.muscleGroup);
    dest.writeString(this.type);
  }
}
