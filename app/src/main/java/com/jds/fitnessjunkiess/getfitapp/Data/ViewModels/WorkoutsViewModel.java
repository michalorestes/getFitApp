package com.jds.fitnessjunkiess.getfitapp.Data.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.jds.fitnessjunkiess.getfitapp.Data.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.Data.Repositories.WorkoutsRepository;

import java.util.List;

public class WorkoutsViewModel extends AndroidViewModel {
  WorkoutsRepository repository;
  LiveData<List<Workout>> data;

  public WorkoutsViewModel(Application application) {
    super(application);
    this.repository = new WorkoutsRepository(application);
    this.data= this.repository.getData();
  }

  public LiveData<List<Workout>> getData() {
    return data;
  }

  public void insert(Workout workout) {
    this.repository.insert(workout);
  }
}
