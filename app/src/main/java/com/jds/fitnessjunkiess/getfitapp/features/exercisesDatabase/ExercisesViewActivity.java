package com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Exercise;
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Workout;
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.WorkoutExerciseAssignment;
import com.jds.fitnessjunkiess.getfitapp.data.viewModels.ExerciseViewModel;
import com.jds.fitnessjunkiess.getfitapp.data.viewModels.WorkoutExerciseAssignmentViewModel;
import com.jds.fitnessjunkiess.getfitapp.data.viewModels.WorkoutsViewModel;
import com.jds.fitnessjunkiess.getfitapp.data.pojo.ExercisesFilter;
import com.jds.fitnessjunkiess.getfitapp.R;
import com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase.adapters.AbstractExercisesAdapter;
import com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase.adapters.ExercisesAdapter;
import com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase.adapters.WorkoutContextExercisesAdapter;

import java.util.ArrayList;
import java.util.List;

public class ExercisesViewActivity extends AppCompatActivity
    implements ExerciseFilterDialog.ActionsInterface, ExercisesAdapter.OnItemMenuClickInterface {

  private AbstractExercisesAdapter recyclerViewerAdapter;
  private ExercisesFilter exerciseFilters;
  private ExerciseViewModel exerciseViewModel;
  private WorkoutsViewModel workoutsViewModel;
  private WorkoutExerciseAssignmentViewModel workoutExerciseAssignmentViewModel;
  List<Workout> workouts;
  private SearchView search;
  private Workout activeWorkout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercises_view);

    activeWorkout = this.getActiveWorkout();
    this.workouts = new ArrayList<>();
    this.exerciseFilters = this.getExerciseFilters();

    this.setUpActionBar();
    this.setUpRecyclerViewer();

    this.workoutExerciseAssignmentViewModel =
        ViewModelProviders.of(this).get(WorkoutExerciseAssignmentViewModel.class);

    if (this.activeWorkout == null) {
      this.workoutsViewModel = ViewModelProviders.of(this).get(WorkoutsViewModel.class);
      this.workoutsViewModel.getData().observe(this, workoutList -> {
        workouts.clear();
        if (workoutList != null) {
          workouts.addAll(workoutList);
        }
      });
    }

    this.exerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel.class);
    this.exerciseViewModel.setFilterMutableLiveData(exerciseFilters);
    this.exerciseViewModel.select().observe(this, exercises -> {
      if (exercises != null) {
        this.recyclerViewerAdapter.updateDataSet(exercises);
      }
    });
  }

  private ExercisesFilter getExerciseFilters() {
    ExercisesFilter exercisesFilter = getIntent().getExtras().getParcelable("exerciseFilters");
    if (exercisesFilter == null) {
      exercisesFilter = new ExercisesFilter("", "");
    }

    return exercisesFilter;
  }

  private Workout getActiveWorkout() {
    return getIntent().getExtras().getParcelable("workoutContext");
  }

  private void setUpActionBar() {
    Toolbar toolbar = findViewById(R.id.toolbar_exercise_view_activity);
    toolbar.setTitle("Exercises");
    setSupportActionBar(toolbar);

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setDisplayShowHomeEnabled(true);
    }
  }

  private void setUpRecyclerViewer() {
    RecyclerView recyclerView = findViewById(R.id.exercise_view_recycle_viewer);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
    recyclerView.setLayoutManager(layoutManager);
    if (this.activeWorkout == null) {
      this.recyclerViewerAdapter = new ExercisesAdapter(this);
    } else {
      this.recyclerViewerAdapter = new WorkoutContextExercisesAdapter(this);
    }

    recyclerView.setAdapter(recyclerViewerAdapter);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.activity_exercises_view, menu);
    this.search = (SearchView) menu.findItem(R.id.search_option).getActionView();
    if (this.activeWorkout != null) {
      menu.findItem(R.id.save_changes_option).setVisible(true);
    }
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    super.onOptionsItemSelected(item);

    switch (item.getItemId()) {
      case R.id.filter_option:
        this.openFiltersDialog();
        break;
      case R.id.search_option:
//        onSearchRequested();
        break;
      case android.R.id.home:
        onBackPressed();
        break;
    }

    return true;
  }

  public void openFiltersDialog() {
    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    ExerciseFilterDialog filterDialog = new ExerciseFilterDialog();
    Bundle bundle = new Bundle();
    bundle.putParcelable("exerciseFilters", this.exerciseFilters);
    filterDialog.setArguments(bundle);
    filterDialog.show(fragmentTransaction, "135555");
  }

  @Override
  public void onPositiveClick(ExercisesFilter exerciseFilters) {
    this.exerciseFilters.types.clear();
    this.exerciseFilters.muscleGroup.clear();
    this.exerciseFilters.types.addAll(exerciseFilters.types);
    this.exerciseFilters.muscleGroup.addAll(exerciseFilters.muscleGroup);

    exerciseViewModel.setFilterMutableLiveData(exerciseFilters);
  }

  @Override
  public Context getAppContext() {
    return getApplicationContext();
  }

  @Override
  public List<Workout> getWorkoutsList() {
    return this.workouts;
  }

  @Override
  public void insertExerciseAssignment(Exercise exercise, Workout workout) {
    WorkoutExerciseAssignment exerciseAssignment = new WorkoutExerciseAssignment();
    exerciseAssignment.setExerciseId(exercise.getId());
    exerciseAssignment.setWorkoutId(workout.getId());
    this.workoutExerciseAssignmentViewModel.insert(exerciseAssignment);
    Toast.makeText(
        getApplicationContext(),
        exercise.getName() + " added to " + workout.getName(),
        Toast.LENGTH_LONG).show();
  }

  @Override
  public void insertExerciseAssignment(Exercise exercise) {
    WorkoutExerciseAssignment exerciseAssignment = new WorkoutExerciseAssignment();
    exerciseAssignment.setExerciseId(exercise.getId());
    exerciseAssignment.setWorkoutId(this.activeWorkout.getId());
    this.workoutExerciseAssignmentViewModel.insert(exerciseAssignment);
    Toast.makeText(
        getApplicationContext(),
        exercise.getName() + " added to " + this.activeWorkout.getName(),
        Toast.LENGTH_LONG).show();
  }
}
