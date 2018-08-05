package com.jds.fitnessjunkiess.getfitapp.ViewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.jds.fitnessjunkiess.getfitapp.Entities.Exercise;
import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.Entities.WorkoutExercise;
import com.jds.fitnessjunkiess.getfitapp.Repositories.WorkoutRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class WorkoutViewModel extends ViewModel {

  private LiveData<List<Workout>> workout;
  private WorkoutRepository workoutData;

  @Inject
  public WorkoutViewModel(WorkoutRepository workoutData) {
    this.workoutData = workoutData;
  }

  public void init(int userId) {
    if (workout != null) {
      return;
    }

    this.workout = this.workoutData.getWorkouts(userId);
  }

  public LiveData<List<Workout>> getWorkout() {
    MutableLiveData<List<Workout>> data = new MutableLiveData<>();
    List<Workout> list = new ArrayList<>();
    Workout w1 = new Workout();
    w1.setUserId(7);
    w1.setName("first workout");
    w1.setId(0);
    w1.setType("Weights");

    Workout w2 = new Workout();
    w2.setUserId(7);
    w2.setName("Super abs");
    w2.setId(1);
    w2.setType("Weights");

    Workout w3 = new Workout();
    w3.setUserId(7);
    w3.setName("Extreme HIIT");
    w3.setId(0);
    w3.setType("Intervals");

    list.add(w1);
    list.add(w2);
    list.add(w3);

    data.setValue(list);
    return workout;
  }

  public LiveData<Workout> addWorkout(Workout workout) {
    return this.workoutData.addWorkout(workout);
  }
}
