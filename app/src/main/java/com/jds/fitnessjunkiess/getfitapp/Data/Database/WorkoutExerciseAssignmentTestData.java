package com.jds.fitnessjunkiess.getfitapp.Data.Database;

import com.jds.fitnessjunkiess.getfitapp.Data.Entities.WorkoutExerciseAssignment;
import java.util.ArrayList;
import java.util.List;

public class WorkoutExerciseAssignmentTestData {
  public static List<WorkoutExerciseAssignment> getData() {
    WorkoutExerciseAssignment workoutExerciseAssignment1 = new WorkoutExerciseAssignment();
    workoutExerciseAssignment1.setId(1);
    workoutExerciseAssignment1.setReps(12);
    workoutExerciseAssignment1.setRestTime("00:00:30");
    workoutExerciseAssignment1.setSets(3);
    workoutExerciseAssignment1.setExerciseId(1);
    workoutExerciseAssignment1.setWorkoutId(1);

    WorkoutExerciseAssignment workoutExerciseAssignment2 = new WorkoutExerciseAssignment();
    workoutExerciseAssignment2.setId(2);
    workoutExerciseAssignment2.setReps(12);
    workoutExerciseAssignment2.setRestTime("00:00:30");
    workoutExerciseAssignment2.setSets(4);
    workoutExerciseAssignment2.setExerciseId(2);
    workoutExerciseAssignment2.setWorkoutId(1);

    WorkoutExerciseAssignment workoutExerciseAssignment3 = new WorkoutExerciseAssignment();
    workoutExerciseAssignment3.setId(3);
    workoutExerciseAssignment3.setReps(12);
    workoutExerciseAssignment3.setLengthTime("00:60:00");
    workoutExerciseAssignment3.setExerciseId(5);
    workoutExerciseAssignment3.setWorkoutId(3);


    WorkoutExerciseAssignment workoutExerciseAssignment4 = new WorkoutExerciseAssignment();
    workoutExerciseAssignment4.setId(4);
    workoutExerciseAssignment4.setReps(12);
    workoutExerciseAssignment4.setRestTime("00:00:30");
    workoutExerciseAssignment4.setSets(3);
    workoutExerciseAssignment4.setExerciseId(1);
    workoutExerciseAssignment4.setWorkoutId(2);

    WorkoutExerciseAssignment workoutExerciseAssignment5 = new WorkoutExerciseAssignment();
    workoutExerciseAssignment5.setId(5);
    workoutExerciseAssignment5.setReps(12);
    workoutExerciseAssignment5.setRestTime("00:00:30");
    workoutExerciseAssignment5.setSets(4);
    workoutExerciseAssignment5.setExerciseId(2);
    workoutExerciseAssignment5.setWorkoutId(2);

    WorkoutExerciseAssignment workoutExerciseAssignment6 = new WorkoutExerciseAssignment();
    workoutExerciseAssignment6.setId(6);
    workoutExerciseAssignment6.setReps(12);
    workoutExerciseAssignment6.setRestTime("00:00:30");
    workoutExerciseAssignment6.setSets(4);
    workoutExerciseAssignment6.setExerciseId(4);
    workoutExerciseAssignment6.setWorkoutId(2);

    List<WorkoutExerciseAssignment> workoutExerciseAssignments = new ArrayList<>();
    workoutExerciseAssignments.add(workoutExerciseAssignment1);
    workoutExerciseAssignments.add(workoutExerciseAssignment2);
    workoutExerciseAssignments.add(workoutExerciseAssignment3);
    workoutExerciseAssignments.add(workoutExerciseAssignment4);
    workoutExerciseAssignments.add(workoutExerciseAssignment5);
    workoutExerciseAssignments.add(workoutExerciseAssignment6);


    return workoutExerciseAssignments;
  }
}
