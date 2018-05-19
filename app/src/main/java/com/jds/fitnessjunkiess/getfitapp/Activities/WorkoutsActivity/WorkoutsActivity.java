package com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.jds.fitnessjunkiess.getfitapp.Entities.User;
import com.jds.fitnessjunkiess.getfitapp.R;

public class WorkoutsActivity extends AppCompatActivity implements WorkoutsListFragment.onWorkoutSelectedInterface {

    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);

        Intent intent = getIntent();
        User u = intent.getParcelableExtra("userData");
        if (u != null) {
            this.userId = u.getId();
        } else {
            this.userId = 0;
        }

        Bundle bundle = new Bundle();
        bundle.putInt("userId", this.userId);
        WorkoutsListFragment workoutsListFragment = new WorkoutsListFragment();
        workoutsListFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .add(R.id.workouts_fragment_container, workoutsListFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getSupportFragmentManager().popBackStackImmediate();
    }

    @Override
    public void onWorkoutSelected(int workoutId) {
        ViewWorkoutFragment viewWorkoutFragment = new ViewWorkoutFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.workouts_fragment_container, viewWorkoutFragment)
                .addToBackStack(null)
                .commit();
    }
}
