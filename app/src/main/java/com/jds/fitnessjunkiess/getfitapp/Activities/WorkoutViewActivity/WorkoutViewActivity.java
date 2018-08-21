package com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutViewActivity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutViewActivity.Adapters.WorkoutExerciseListInterface;
import com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutViewActivity.Fragments.WorkoutExercisesFragment;
import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;

public class WorkoutViewActivity extends AppCompatActivity implements WorkoutExerciseListInterface {

  private static final String TAG = "WORKOUT_ACTIVITY";
  private Workout workout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_workout_view);
    Intent intent = getIntent();
    this.workout = intent.getParcelableExtra("workoutData");
    Log.i(TAG, workout.toString());
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    WorkoutExercisesFragment workoutExercisesFragment = new WorkoutExercisesFragment();
    fragmentTransaction.add(R.id.fragment_container, workoutExercisesFragment);
    fragmentTransaction.commit();
  }

  @Override
  public Workout getWorkout() {
    return this.workout;
  }
}
