package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jds.fitnessjunkiess.getfitapp.Activities.LoginActivity.LoginActivity;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerComponents.DaggerUserViewModelFactoryComponent;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerComponents.DaggerWorkoutViewModelFactoryComponent;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerComponents.UserViewModelFactoryComponent;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerComponents.WorkoutViewModelFactoryComponent;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerModules.UserViewModelFactoryModule;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerModules.WorkoutViewModelFactoryModule;
import com.jds.fitnessjunkiess.getfitapp.Entities.User;
import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.Factories.UserViewModelFactory;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.Factories.WorkoutViewModelFactory;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.UserViewModel;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.WorkoutViewModel;

public class DisplayDataFragment extends Fragment {

    WorkoutViewModel workoutViewModel;
    WorkoutViewModelFactory workoutViewModelFactory;
    UserViewModel userViewModel;
    UserViewModelFactory userViewModelFactory;
    Button button;

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
            if (w != null){
                for (Workout workout : w){
                    Log.i("***", workout.toString());
                }
            }

        });

        UserViewModelFactoryComponent userViewModelFactoryComponent =
                DaggerUserViewModelFactoryComponent
                .builder()
                .userViewModelFactoryModule(new UserViewModelFactoryModule())
                .build();

        this.userViewModelFactory =
                userViewModelFactoryComponent
                .provideUserViewModelFactory();

        this.userViewModel = ViewModelProviders.of(this, this.userViewModelFactory)
                .get(UserViewModel.class);

        userViewModel.init("michalorestes@gmail.com");
        userViewModel.getUser().observe(this, u -> {
            if (u != null){
                Log.i("***", "Got the user: " + u.getEmail() + " " + u.getUsername());
            }
        });

        User user = new User();
        user.setEmail("trump@mail.com");
        user.setUsername("hahahtramp");
        userViewModel.addUser(user);

        Log.i("**", "Could be working");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstances){
        View rootView =
                inflater.inflate(R.layout.display_data_fragment, viewGroup, false);

        this.button = (Button) rootView.findViewById(R.id.startActivityBtn);
        this.button.setOnClickListener(this::startLoginScreen);

        return rootView;
    }

    public void startLoginScreen(View view){
        Log.i("**", "screen starting");
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }
}
