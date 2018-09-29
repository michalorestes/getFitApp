package com.jds.fitnessjunkiess.getfitapp.data.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Exercise;
import com.jds.fitnessjunkiess.getfitapp.data.repositories.ExercisesRepository;
import com.jds.fitnessjunkiess.getfitapp.data.pojo.ExercisesFilters;

import java.util.List;

public class ExerciseViewModel extends AndroidViewModel {
  ExercisesRepository repository;
  MediatorLiveData<List<Exercise>> data = new MediatorLiveData<>();

  private MutableLiveData<ExercisesFilters> filterMutableLiveData;

  public ExerciseViewModel(Application application) {
    super(application);

    this.repository = new ExercisesRepository(application);
    this.filterMutableLiveData = new MutableLiveData<>();

    ExercisesFilters filter = new ExercisesFilters();
    this.filterMutableLiveData.setValue(filter);

    LiveData<List<Exercise>> source = Transformations.switchMap(
        this.filterMutableLiveData, value -> repository.filterSelect(
            this.filterMutableLiveData.getValue()
        )
    );

    this.data.addSource(source, val -> this.data.setValue(val));
  }

  public LiveData<List<Exercise>> select() {
    return data;
  }

  public void insert(Exercise exercise) {
    this.repository.insert(exercise);
  }

  public void setFilterMutableLiveData(ExercisesFilters exercisesFilter) {
    this.filterMutableLiveData.setValue(exercisesFilter);
  }

  public ExercisesFilters getExercisesFilter() {
    return this.filterMutableLiveData.getValue();
  }
}
