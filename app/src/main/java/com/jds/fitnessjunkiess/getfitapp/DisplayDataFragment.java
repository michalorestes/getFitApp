package com.jds.fitnessjunkiess.getfitapp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DisplayDataFragment extends Fragment {

    WorkoutViewModel workoutViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int workoutId = 1;

//        workoutViewModel = ViewModelProviders.of(this).get(WorkoutViewModel.class);
//        workoutViewModel.init(workoutId);
//
////        workoutViewModel.getWorkout().observe(this, workout -> {
////            Log.i("**", workout.toString());
////        });





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstances){
        return inflater.inflate(R.layout.display_data_fragment, viewGroup, false);
    }

}
