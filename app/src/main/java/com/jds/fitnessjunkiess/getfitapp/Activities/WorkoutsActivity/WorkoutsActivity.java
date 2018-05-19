package com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutsActivity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.jds.fitnessjunkiess.getfitapp.R;

public class WorkoutsActivity extends AppCompatActivity implements WorkoutsListFragment.onWorkoutSelectedInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);

//        Intent intent = getIntent();
//        User u = intent.getParcelableExtra("userData");
//        if (u != null){
//            u.toString();
//        }

        WorkoutsListFragment workoutsListFragment = new WorkoutsListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .add(R.id.workouts_fragment_container, workoutsListFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        getSupportFragmentManager().popBackStackImmediate();
//        Log.d("CDA", "onBackPressed Called");

    }

    @Override
    public void onWorkoutSelected(int workoutId) {
        Log.i("****","workout : " + workoutId);
        ViewWorkoutFragment viewWorkoutFragment = new ViewWorkoutFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.workouts_fragment_container, viewWorkoutFragment)
                .addToBackStack(null)
                .commit();
    }
}
