package com.jds.fitnessjunkiess.getfitapp.ViewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.Repositories.WorkoutRepository;

import java.util.List;

import javax.inject.Inject;

public class WorkoutViewModel extends ViewModel {

    private LiveData<List<Workout>> workout;
    private WorkoutRepository workoutData;

    @Inject
    public WorkoutViewModel(WorkoutRepository workoutData){
        this.workoutData = workoutData;
    }

    public void init(int userId){
        if (workout != null){
            return;
        }

        this.workout = this.workoutData.getWorkouts(userId);
    }

    public LiveData<List<Workout>> getWorkout() {
        return workout;
    }

    public LiveData<Workout> addWorkout(Workout workout) {
        return this.workoutData.addWorkout(workout);
    }
}
