package com.jds.fitnessjunkiess.getfitapp.ViewModels.Factories;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.jds.fitnessjunkiess.getfitapp.Repositories.WorkoutRepository;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.WorkoutViewModel;

import javax.inject.Inject;

public class WorkoutViewModelFactory implements ViewModelProvider.Factory {

  private final WorkoutRepository workoutRepository;

  @Inject
  public WorkoutViewModelFactory(WorkoutRepository workoutRepository) {
    this.workoutRepository = workoutRepository;
  }

  @NonNull
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    return (T) new WorkoutViewModel(this.workoutRepository);
  }
}
