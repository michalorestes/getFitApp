package com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutsActivity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerComponents.DaggerWorkoutViewModelFactoryComponent;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerComponents.WorkoutViewModelFactoryComponent;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerModules.WorkoutViewModelFactoryModule;
import com.jds.fitnessjunkiess.getfitapp.Entities.User;
import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.Factories.WorkoutViewModelFactory;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.WorkoutViewModel;

import java.util.ArrayList;
import java.util.List;

public class WorkoutsActivity
        extends AppCompatActivity implements WorkoutsListFragment.onWorkoutSelectedInterface {

    private WorkoutsListFragment workoutsListFragment;
    private List<Workout> workouts;
    private WorkoutViewModel workoutViewModel;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);
        this.user = new User();
        this.workouts = new ArrayList<>();

        Intent intent = getIntent();
        User u = intent.getParcelableExtra("userData");
        int userId;
        if (u != null) {
            this.user.setId(u.getId());
        } else {
            //TODO: Ideally stop app execution if no user ID is available
            userId = 0;
        }

        this.workoutsListFragment = new WorkoutsListFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .add(R.id.workouts_fragment_container, workoutsListFragment)
                .commit();

        WorkoutViewModelFactoryComponent workoutViewModelFactoryComponent =
                DaggerWorkoutViewModelFactoryComponent
                        .builder()
                        .workoutViewModelFactoryModule(new WorkoutViewModelFactoryModule())
                        .build();

        WorkoutViewModelFactory workoutViewModelFactory = workoutViewModelFactoryComponent
                .provideWorkoutViewModelFactory();

        this.workoutViewModel =
                ViewModelProviders.of(this, workoutViewModelFactory)
                .get(WorkoutViewModel.class);

        workoutViewModel.init(this.user.getId());
        workoutViewModel.getWorkout().observe(this, w -> {
            this.workouts = w;
            this.workoutsListFragment.updateWorkoutsList(this.workouts, false);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getSupportFragmentManager().popBackStackImmediate();
    }

    @Override
    public void onWorkoutSelected(int workoutIndex) {
        Workout workout = this.workouts.get(workoutIndex);

        Gson gson = new Gson();
        String workoutJson = gson.toJson(workout);

        Bundle bundle = new Bundle();
        bundle.putString("workout", workoutJson);
        ViewWorkoutFragment viewWorkoutFragment = new ViewWorkoutFragment();
        viewWorkoutFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.workouts_fragment_container, viewWorkoutFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public List<Workout> onWorkoutListRequested() {
        return this.workouts;
    }

    @Override
    public void onAddWorkout(String workoutName) {
        Workout workout = new Workout();
        workout.setName(workoutName);
        workout.setUserId(this.user.getId());
        this.workoutViewModel.addWorkout(workout).observe(this, w -> {
            this.workouts.add(w);
            this.workoutsListFragment.updateWorkoutsList(this.workouts, true);
        });
    }
}
