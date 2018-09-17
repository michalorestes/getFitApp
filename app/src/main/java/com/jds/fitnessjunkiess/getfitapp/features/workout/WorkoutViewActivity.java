package com.jds.fitnessjunkiess.getfitapp.features.workout;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase.AddExerciseActivity;
import com.jds.fitnessjunkiess.getfitapp.features.workout.adapters.WorkoutViewViewAdapter;
import com.jds.fitnessjunkiess.getfitapp.data.viewModels.WorkoutExerciseViewModel;
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;

public class WorkoutViewActivity extends AppCompatActivity {

  private static final String TAG = "WorkoutViewActivity";
  private Workout workout;
  private RecyclerView recyclerView;
  private WorkoutViewViewAdapter workoutViewViewAdapter;
  private WorkoutExerciseViewModel workoutExerciseViewModel;
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
    
    this.workoutExerciseViewModel = ViewModelProviders.of(this).get(WorkoutExerciseViewModel.class); 
    
    this.workoutExerciseViewModel.selectAll(this.workout.getId()).observe(this, workoutExercises -> {
      Log.d(TAG, "onChanged: we have some data :)");
      if (workoutExercises != null) {
        workoutViewViewAdapter.updateDataSet(workoutExercises);
      }
    });
    
    this.recyclerView = findViewById(R.id.workout_view_recycle_viewer);
    RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
    this.recyclerView.setLayoutManager(manager);
    this.workoutViewViewAdapter = new WorkoutViewViewAdapter();
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
