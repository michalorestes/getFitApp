package com.jds.fitnessjunkiess.getfitapp.data.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.jds.fitnessjunkiess.getfitapp.data.dataModels.MuscleGroup;
import com.jds.fitnessjunkiess.getfitapp.data.repositories.MuscleGroupRepository;

import java.util.List;

public class MuscleGroupViewModel extends AndroidViewModel {
  MuscleGroupRepository repository;
  LiveData<List<MuscleGroup>> data;

  public MuscleGroupViewModel(Application application) {
    super(application);
    this.repository = new MuscleGroupRepository(application);
    this.data = this.repository.selectAll();
  }

  public LiveData<List<MuscleGroup>> selectAll() {
    return data;
  }

  public void insert(MuscleGroup muscleGroup) {
    this.repository.insert(muscleGroup);
  }
}
