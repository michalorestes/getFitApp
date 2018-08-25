package com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutViewActivity;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.jds.fitnessjunkiess.getfitapp.Activities.AddExercise.AddExerciseActivity;
import com.jds.fitnessjunkiess.getfitapp.Dialogs.WorkoutDetailsDialog;
import com.jds.fitnessjunkiess.getfitapp.Data.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;

public class WorkoutViewActivity extends AppCompatActivity {

  private static final String TAG = "WorkoutViewActivity";
  private Workout workout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_workout_view);

    Intent intent = getIntent();
    this.workout = intent.getParcelableExtra("workoutData");

    Toolbar toolbar = findViewById(R.id.toolbar);
    toolbar.setTitle(workout.getName());
    setSupportActionBar(toolbar);
    ActionBar actionBar = getSupportActionBar();

    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setDisplayShowHomeEnabled(true);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.activity_toolbar_workout_view, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_add_exercise:
        Intent intent = new Intent(getApplicationContext(), AddExerciseActivity.class);
        startActivity(intent);
        break;
      case R.id.action_edit_workout:
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        WorkoutDetailsDialog dialogTest = new WorkoutDetailsDialog();
        dialogTest.show(fragmentTransaction, "tag2");
        break;
      case android.R.id.home:
        onBackPressed();
        break;
    }

    return true;
  }
}
