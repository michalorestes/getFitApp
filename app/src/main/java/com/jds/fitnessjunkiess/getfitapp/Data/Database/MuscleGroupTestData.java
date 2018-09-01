package com.jds.fitnessjunkiess.getfitapp.Data.Database;

import com.jds.fitnessjunkiess.getfitapp.Data.Entities.MuscleGroup;

import java.util.ArrayList;
import java.util.List;

public class MuscleGroupTestData {

  public static List<MuscleGroup> getData() {
    MuscleGroup m1 = new MuscleGroup();
    m1.setName("ABS");

    MuscleGroup m2 = new MuscleGroup();
    m2.setName("Back");

    MuscleGroup m3 = new MuscleGroup();
    m3.setName("Biceps");

    MuscleGroup m4 = new MuscleGroup();
    m4.setName("Legs");

    MuscleGroup m5 = new MuscleGroup();
    m5.setName("Triceps");

    MuscleGroup m6 = new MuscleGroup();
    m6.setName("Chest");

    List<MuscleGroup> muscleGroups = new ArrayList<>();
    muscleGroups.add(m1);
    muscleGroups.add(m2);
    muscleGroups.add(m3);
    muscleGroups.add(m4);
    muscleGroups.add(m5);
    muscleGroups.add(m6);

    return muscleGroups;
  }

}
