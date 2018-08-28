package com.jds.fitnessjunkiess.getfitapp.Data.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import com.jds.fitnessjunkiess.getfitapp.Data.Entities.WorkoutExerciseAssignment;
import com.jds.fitnessjunkiess.getfitapp.Data.Repositories.WorkoutExerciseRepository;
import com.jds.fitnessjunkiess.getfitapp.Pojo.WorkoutExercise;

import java.util.List;

public class WorkoutExerciseViewModel extends AndroidViewModel {

  private WorkoutExerciseRepository repository;
  private LiveData<List<WorkoutExercise>> data;
  private Application application;

  public WorkoutExerciseViewModel(@NonNull Application application) {
    super(application);
    this.application = application;
  }

  public LiveData<List<WorkoutExercise>> selectAll(int workoutId) {

    if (this.repository == null) {
      this.repository = new WorkoutExerciseRepository(this.application, workoutId);
      this.data = this.repository.getWorkoutExercises(workoutId);
    }

    return this.data;
  }

  public void insert(WorkoutExerciseAssignment workoutExerciseAssignment) {
    this.repository.insert(workoutExerciseAssignment);
  }
}
