package com.jds.fitnessjunkiess.getfitapp.Data.Database;

import com.jds.fitnessjunkiess.getfitapp.Data.DataModels.Workout;
import java.util.ArrayList;
import java.util.List;

public class WorkoutsTestData {
  public static List<Workout> getData() {

    Workout workout1 = new Workout();
    workout1.setUserId(7);
    workout1.setName("Massive biceps");
    workout1.setId(1);
    workout1.setSchedule("Monday,Tuesday,Friday");
    workout1.setType("Weights");

    Workout workout2 = new Workout();
    workout2.setUserId(7);
    workout2.setName("Shreded");
    workout2.setId(2);
    workout2.setSchedule("Tuesday,Friday");
    workout2.setType("Interval");

    Workout workout3 = new Workout();
    workout3.setUserId(7);
    workout3.setName("6 pack ABS");
    workout3.setId(3);
    workout3.setSchedule("Saturday");
    workout3.setType("Cardio");

    List<Workout> workouts = new ArrayList<>();
    workouts.add(workout1);
    workouts.add(workout2);
    workouts.add(workout3);

    return workouts;
  }
}
