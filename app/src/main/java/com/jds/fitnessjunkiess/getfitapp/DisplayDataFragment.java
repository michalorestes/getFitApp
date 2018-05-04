package com.jds.fitnessjunkiess.getfitapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jds.fitnessjunkiess.getfitapp.DI.DaggerComponents.DaggerWorkoutRepositoryComponent;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerComponents.WorkoutRepositoryComponent;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerModules.WorkoutRepositoryModule;
import com.jds.fitnessjunkiess.getfitapp.Repositories.WorkoutDataRepository;

public class DisplayDataFragment extends Fragment {

    WorkoutViewModel workoutViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int workoutId = 1;

        WorkoutRepositoryComponent workoutDataRepository =
                DaggerWorkoutRepositoryComponent
                .builder()
                .workoutRepositoryModule(new WorkoutRepositoryModule())
                .build();

        WorkoutDataRepository workoutDataRepository1 = workoutDataRepository.provideWorkoutDataRepository();

        workoutDataRepository1.getWorkouts(1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstances){
        return inflater.inflate(R.layout.display_data_fragment, viewGroup, false);
    }

}
