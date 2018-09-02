package com.jds.fitnessjunkiess.getfitapp.Data.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.jds.fitnessjunkiess.getfitapp.Data.Entities.Exercise;
import com.jds.fitnessjunkiess.getfitapp.Data.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.Data.Repositories.ExercisesRepository;
import com.jds.fitnessjunkiess.getfitapp.Data.Repositories.WorkoutsRepository;

import java.util.List;

public class ExerciseViewModel extends AndroidViewModel {
  ExercisesRepository repository;
  LiveData<List<Exercise>> data;

  public ExerciseViewModel(Application application) {
    super(application);
    this.data = new MutableLiveData<>();
    this.repository = new ExercisesRepository(application);
  }

  public void setFilters(String muscleGroups, String type) {
    this.data = this.repository.filterSelect(muscleGroups, type);
  }

  public void selectAll() {
    this.data = this.repository.selectAll();
  }

  public LiveData<List<Exercise>> select() {
    return data;
  }

  public void insert(Exercise exercise) {
    this.repository.insert(exercise);
  }
}
