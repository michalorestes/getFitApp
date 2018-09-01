package com.jds.fitnessjunkiess.getfitapp.Data.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "muscle_groups")
public class MuscleGroup {
  @PrimaryKey(autoGenerate = true)
  private int id;
  private String name;

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

  @Override
  public String toString() {
    return "MuscleGroup {" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
