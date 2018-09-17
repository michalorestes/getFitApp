package com.jds.fitnessjunkiess.getfitapp.data.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.WorkoutExerciseAssignment;
import com.jds.fitnessjunkiess.getfitapp.data.repositories.WorkoutExerciseAssignmentRepository;

public class WorkoutExerciseAssignmentViewModel extends AndroidViewModel {

  private WorkoutExerciseAssignmentRepository workoutExerciseAssignmentRepository;

  public WorkoutExerciseAssignmentViewModel(Application application) {
    super(application);
    this.workoutExerciseAssignmentRepository = new WorkoutExerciseAssignmentRepository(application);
  }

  public void insert(WorkoutExerciseAssignment workoutExerciseAssignment) {
    this.workoutExerciseAssignmentRepository.insert(workoutExerciseAssignment);
  }
}
