package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jds.fitnessjunkiess.getfitapp.DI.DaggerComponents.DaggerWorkoutViewModelFactoryComponent;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerComponents.WorkoutViewModelFactoryComponent;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerModules.WorkoutViewModelFactoryModule;
import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.Factories.WorkoutViewModelFactory;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.WorkoutViewModel;

import javax.inject.Inject;

public class DisplayDataFragment extends Fragment {

    WorkoutViewModel workoutViewModel;
    //TODO: This doesn't get injected to null is being passed to ViewProviderModel

    WorkoutViewModelFactory workoutViewModelFactory;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        WorkoutViewModelFactoryComponent workoutViewModelFactoryComponent =
                DaggerWorkoutViewModelFactoryComponent
                .builder()
                .workoutViewModelFactoryModule(new WorkoutViewModelFactoryModule())
                .build();

        this.workoutViewModelFactory =
                workoutViewModelFactoryComponent
                .provideWorkoutViewModelFactory();

        this.workoutViewModel =  ViewModelProviders.of(this, this.workoutViewModelFactory)
                .get(WorkoutViewModel.class);

        workoutViewModel.init(1);
        workoutViewModel.getWorkout().observe(this, w -> {
            for (Workout workout : w){
                Log.i("**", workout.toString());
            }
        });


//        for (Workout w : workoutViewModel.getWorkout().getValue()){
//            Log.i("**", w.toString());
//        }


        Log.i("**", "Could be working");




//        WorkoutRepositoryComponent workoutDataRepository =
//                DaggerWorkoutRepositoryComponent
//                .builder()
//                .workoutRepositoryModule(new WorkoutRepositoryModule())
//                .build();
//
//        WorkoutRepository workoutDataRepository1 =
//                workoutDataRepository.provideWorkoutDataRepository();
//
//        workoutDataRepository1.getWorkouts(1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstances){
        return inflater.inflate(R.layout.display_data_fragment, viewGroup, false);
    }
}
