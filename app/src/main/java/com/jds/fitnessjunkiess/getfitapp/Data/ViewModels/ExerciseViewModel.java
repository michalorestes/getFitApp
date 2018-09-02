package com.jds.fitnessjunkiess.getfitapp.Data.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import com.jds.fitnessjunkiess.getfitapp.Data.Entities.Exercise;
import com.jds.fitnessjunkiess.getfitapp.Data.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.Data.Repositories.ExercisesRepository;
import com.jds.fitnessjunkiess.getfitapp.Data.Repositories.WorkoutsRepository;

import java.util.List;

public class ExerciseViewModel extends AndroidViewModel {
  ExercisesRepository repository;
  MediatorLiveData<List<Exercise>> data = new MediatorLiveData<>();

  private MutableLiveData<ExercisesFilter> filterMutableLiveData;

  public ExerciseViewModel(Application application) {
    super(application);
    this.repository = new ExercisesRepository(application);
    this.filterMutableLiveData = new MutableLiveData<>();

    ExercisesFilter filter = new ExercisesFilter();
    filter.muscleGroup = "";
    filter.type = "";
    this.filterMutableLiveData.setValue(filter);

    LiveData<List<Exercise>> source = Transformations.switchMap(
        this.filterMutableLiveData, value -> repository.filterSelect(value.muscleGroup, value.type)
    );

    this.data.addSource(source, val -> this.data.setValue(val));
  }

  public void setFilters(String muscleGroups, String type) {

  }

  public void selectAll() {
//    this.data = this.repository.selectAll();
  }

  public LiveData<List<Exercise>> select() {
    return data;
  }

  public void insert(Exercise exercise) {
    this.repository.insert(exercise);
  }

  public void setFilterMutableLiveData(String muscleGroups, String type) {
    ExercisesFilter filter1 = new ExercisesFilter();
    filter1.muscleGroup = muscleGroups;
    filter1.type = type;
    this.filterMutableLiveData.setValue(filter1);
  }
}
