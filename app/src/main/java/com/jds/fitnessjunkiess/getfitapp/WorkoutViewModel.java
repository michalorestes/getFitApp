package com.jds.fitnessjunkiess.getfitapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.Repositories.WorkoutDataRepository;

import java.util.List;

import javax.inject.Inject;

public class WorkoutViewModel extends ViewModel {
    private int workoutId;
    private LiveData<List<Workout>> workout;
    private WorkoutDataRepository workoutData;

    @Inject
    public WorkoutViewModel(WorkoutDataRepository workoutData){
        this.workoutData = workoutData;
    }

    public WorkoutViewModel(){
        this.workoutData = workoutData;
    }

    public void init(int workoutId){
        if (workout != null){
            return;
        }

        this.workout = this.workoutData.getWorkouts(workoutId);
    }

    public LiveData<List<Workout>> getWorkout() {
        return workout;
    }
}
