package com.jds.fitnessjunkiess.getfitapp.Data.Database;

import com.jds.fitnessjunkiess.getfitapp.Data.Entities.Exercise;
import java.util.ArrayList;
import java.util.List;

public class ExercisesTestData {
  public static final List<Exercise> getData() {
    Exercise exercise = new Exercise();
    exercise.setId(1);
    exercise.setName("Pull ups ");
    exercise.setInstructions("Grab the handle and pull yourself upwards.");
    exercise.setMuscleGroups("Biceps, back");
    exercise.setType("Body weight");
    exercise.setPicture("https://nourl.com/img.png");

    Exercise exercise2 = new Exercise();
    exercise2.setId(2);
    exercise2.setName("Biceps curl");
    exercise2.setInstructions("Pull the weight upwards.");
    exercise2.setMuscleGroups("Biceps");
    exercise2.setType("Free weights");
    exercise2.setPicture("https://nourl.com/img.png");

    Exercise exercise3 = new Exercise();
    exercise3.setId(3);
    exercise3.setName("Crunches");
    exercise3.setInstructions("Lay down on the floow and exercise your abs.");
    exercise3.setMuscleGroups("ABS");
    exercise3.setType("Body weight");
    exercise3.setPicture("https://nourl.com/img.png");

    Exercise exercise4 = new Exercise();
    exercise4.setId(4);
    exercise4.setName("Deadlift");
    exercise4.setInstructions("Lift up heavy weights.");
    exercise4.setMuscleGroups("Lower back");
    exercise4.setType("Weights");
    exercise4.setPicture("https://nourl.com/img.png");

    Exercise exercise5 = new Exercise();
    exercise5.setId(5);
    exercise5.setName("Trademil Run");
    exercise5.setInstructions("Continue running");
    exercise5.setType("Cardio");
    exercise5.setPicture("https://nourl.com/img.png");

    List<Exercise> exercises = new ArrayList<>();
    exercises.add(exercise);
    exercises.add(exercise2);
    exercises.add(exercise3);
    exercises.add(exercise4);
    exercises.add(exercise5);

    return exercises;
  }
}
