package com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutViewActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;

public class WorkoutViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_view);
        Intent intent = getIntent();
        Workout w = intent.getParcelableExtra("workoutData");
        Log.i("ViewWorkout", w.toString());
    }
}
