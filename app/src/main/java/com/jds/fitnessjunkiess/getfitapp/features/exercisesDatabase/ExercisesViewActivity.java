package com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase;

import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Exercise;
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Workout;
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.WorkoutExerciseAssignment;
import com.jds.fitnessjunkiess.getfitapp.data.viewModels.ExerciseViewModel;
import com.jds.fitnessjunkiess.getfitapp.data.viewModels.WorkoutExerciseAssignmentViewModel;
import com.jds.fitnessjunkiess.getfitapp.data.viewModels.WorkoutsViewModel;
import com.jds.fitnessjunkiess.getfitapp.data.pojo.ExercisesFilters;
import com.jds.fitnessjunkiess.getfitapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ExercisesViewActivity extends AppCompatActivity
    implements ExerciseFilterDialog.ActionsInterface,
    ExercisesListFragment.OnFragmentInteractionListener,
    AddCustomExerciseDialog.OnFragmentInteractionInterface {

  private ExercisesFilters exerciseFilters;
  private ExerciseViewModel exerciseViewModel;
  private WorkoutExerciseAssignmentViewModel workoutExerciseAssignmentViewModel;
  private List<Workout> workouts;
  private Workout activeWorkout;
  private ExercisesListFragment exercisesListFragment;
  private WorkoutsViewModel workoutsViewModel;
  private FloatingActionButton addCustomExerciseBtn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercises_view);

    this.addCustomExerciseBtn = this.findViewById(R.id.add_custom_exercise_button);
    this.addCustomExerciseBtn.setOnClickListener( v -> {
      AddCustomExerciseDialog addCustomExerciseDialog = new AddCustomExerciseDialog();
      addCustomExerciseDialog.show(getSupportFragmentManager(), "addCustomExerciseDialog");
    });

    activeWorkout = this.getIntent().getExtras().getParcelable("workoutContext");
    this.workouts = new ArrayList<>();
    this.exerciseFilters = this.getExerciseFilters();
    this.exercisesListFragment = new ExercisesListFragment();
    this.setUpActionBar();

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction
        .add(R.id.exercise_list_fragment_container, this.exercisesListFragment)
        .commit();

    this.workoutExerciseAssignmentViewModel =
        ViewModelProviders.of(this).get(WorkoutExerciseAssignmentViewModel.class);
    this.workoutsViewModel = ViewModelProviders.of(this).get(WorkoutsViewModel.class);
    this.exerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel.class);
  }

  @Override
  protected void onStart() {
    super.onStart();
    if (this.activeWorkout == null) {
      workoutsViewModel.getData().observe(this, workoutList -> {
        workouts.clear();
        if (workoutList != null) {
          workouts.addAll(workoutList);
        }
      });
    }

    this.exerciseViewModel.setFilterMutableLiveData(exerciseFilters);
    this.exerciseViewModel.select().observe(this, exercises -> {
      if (exercises != null) {
        this.exercisesListFragment.updateDataSet(exercises);
      }
    });
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    super.onOptionsItemSelected(item);

    switch (item.getItemId()) {
      case R.id.filter_option:
        this.openFiltersDialog();
        break;
      case android.R.id.home:
        onBackPressed();
        break;
    }

    return true;
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

  private ExercisesFilters getExerciseFilters() {
    ExercisesFilters exercisesFilter = getIntent().getExtras().getParcelable("exerciseFilters");
    if (exercisesFilter == null) {
      exercisesFilter = new ExercisesFilters();
    }

    return exercisesFilter;
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
  public Workout getActiveWorkout() {
    return this.activeWorkout;
  }

  @Override
  public List<Workout> getWorkoutsList() {
    return this.workouts;
  }

  @Override
  public void insertExerciseAssignment(WorkoutExerciseAssignment workoutExerciseAssignment) {
    this.workoutExerciseAssignmentViewModel.insert(workoutExerciseAssignment);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.activity_exercises_view, menu);

    return true;
  }

  @Override
  public void onPositiveClick(ExercisesFilters exerciseFilters) {
    this.exerciseFilters.getTypes().clear();
    this.exerciseFilters.getMuscleGroups().clear();
    this.exerciseFilters.getTypes().addAll(exerciseFilters.getTypes());
    this.exerciseFilters.getMuscleGroups().addAll(exerciseFilters.getMuscleGroups());

    exerciseViewModel.setFilterMutableLiveData(exerciseFilters);
  }

  @Override
  public void saveCustomExercise(@NotNull Exercise exercise) {
    this.exerciseViewModel.insert(exercise);
  }
}
