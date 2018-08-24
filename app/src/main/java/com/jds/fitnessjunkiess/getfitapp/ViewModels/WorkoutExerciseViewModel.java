package com.jds.fitnessjunkiess.getfitapp.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import com.jds.fitnessjunkiess.getfitapp.Entities.WorkoutExercise;
import com.jds.fitnessjunkiess.getfitapp.Repositories.WorkoutExerciseRepository;

import java.util.List;

public class WorkoutExerciseViewModel extends AndroidViewModel {

  private WorkoutExerciseRepository repository;
  private LiveData<List<WorkoutExercise>> data;

  public WorkoutExerciseViewModel(@NonNull Application application) {
    super(application);
    this.repository = new WorkoutExerciseRepository(application);
  }

  public LiveData<List<WorkoutExercise>> selectAll() {
    if (this.data == null) {
      this.data = this.repository.selectAll();
    }

    return this.data;
  }

  public void insert(WorkoutExercise workoutExercise) {
    this.repository.insert(workoutExercise);
  }
}
