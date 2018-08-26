package com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutViewActivity;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.jds.fitnessjunkiess.getfitapp.Activities.AddExercise.AddExerciseActivity;
import com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutViewActivity.Adapters.WorkoutViewViewAdapter;
import com.jds.fitnessjunkiess.getfitapp.Data.Entities.WorkoutExercise;
import com.jds.fitnessjunkiess.getfitapp.Dialogs.WorkoutDetailsDialog;
import com.jds.fitnessjunkiess.getfitapp.Data.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;

import java.util.ArrayList;
import java.util.List;

public class WorkoutViewActivity extends AppCompatActivity {

  private static final String TAG = "WorkoutViewActivity";
  private Workout workout;
  private RecyclerView recyclerView;
  private WorkoutViewViewAdapter workoutViewViewAdapter;

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
    //TODO: test data to be removed
    WorkoutExercise workoutExercise = new WorkoutExercise();
    workoutExercise.setReps(12);
    workoutExercise.setSets(5);
    workoutExercise.setExerciseName("test name");

    WorkoutExercise workoutExercise2 = new WorkoutExercise();
    workoutExercise2.setReps(9);
    workoutExercise2.setSets(3);
    workoutExercise2.setExerciseName("Push ups");

    List<WorkoutExercise> workoutExercises = new ArrayList<>();
    workoutExercises.add(workoutExercise);
    workoutExercises.add(workoutExercise2);

    this.recyclerView = findViewById(R.id.workout_view_recycle_viewer);
    RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
    this.recyclerView.setLayoutManager(manager);
    this.workoutViewViewAdapter = new WorkoutViewViewAdapter();

    this.workoutViewViewAdapter.updateDataSet(workoutExercises);

    this.recyclerView.setAdapter(this.workoutViewViewAdapter);
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
