package com.jds.fitnessjunkiess.getfitapp.Data.Database;

import com.jds.fitnessjunkiess.getfitapp.Data.DataModels.Exercise;
import com.jds.fitnessjunkiess.getfitapp.Pojo.ExerciseTypes;
import com.jds.fitnessjunkiess.getfitapp.Pojo.MuscleGroupKeys;
import com.jds.fitnessjunkiess.getfitapp.Pojo.MuscleGroups;

import java.util.ArrayList;
import java.util.List;

public class ExercisesTestData {
  public static final List<Exercise> getData() {
    Exercise exercise = new Exercise();
    exercise.setId(1);
    exercise.setName("Pull ups ");
    exercise.setInstructions("Grab the handle and pull yourself upwards.");
    exercise.setMuscleGroupsByKey(MuscleGroupKeys.PRIMARY, MuscleGroups.BACK);
    exercise.setMuscleGroupsByKey(MuscleGroupKeys.OTHER, MuscleGroups.BICEPS);
    exercise.setType(ExerciseTypes.BODY_WEIGHT);
    exercise.setPicture("https://nourl.com/img.png");

    Exercise exercise2 = new Exercise();
    exercise2.setId(2);
    exercise2.setName("Biceps curl");
    exercise2.setInstructions("Pull the weight upwards.");
    exercise2.setMuscleGroupsByKey(MuscleGroupKeys.PRIMARY, MuscleGroups.BICEPS);
    exercise2.setType(ExerciseTypes.WEIGHTS);
    exercise2.setPicture("https://nourl.com/img.png");

    Exercise exercise3 = new Exercise();
    exercise3.setId(3);
    exercise3.setName("Crunches");
    exercise3.setInstructions("Lay down on the floow and exercise your abs.");
    exercise3.setMuscleGroupsByKey(MuscleGroupKeys.PRIMARY, MuscleGroups.ABS);
    exercise3.setType(ExerciseTypes.BODY_WEIGHT);
    exercise3.setPicture("https://nourl.com/img.png");

    Exercise exercise4 = new Exercise();
    exercise4.setId(4);
    exercise4.setName("Deadlift");
    exercise4.setInstructions("Lift up heavy weights.");
    exercise4.setMuscleGroupsByKey(MuscleGroupKeys.PRIMARY, MuscleGroups.BACK);
    exercise4.setMuscleGroupsByKey(MuscleGroupKeys.OTHER, MuscleGroups.LEGS);
    exercise4.setMuscleGroupsByKey(MuscleGroupKeys.OTHER, MuscleGroups.ABS);
    exercise4.setType(ExerciseTypes.WEIGHTS);
    exercise4.setPicture("https://nourl.com/img.png");

    Exercise exercise5 = new Exercise();
    exercise5.setId(5);
    exercise5.setName("Trademil Run");
    exercise5.setInstructions("Continue running");
    exercise5.setType(ExerciseTypes.CARDIO);
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
