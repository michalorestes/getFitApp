package com.jds.fitnessjunkiess.getfitapp.Activities.ExercisesView;

import android.arch.lifecycle.ViewModelProviders;
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
import com.jds.fitnessjunkiess.getfitapp.Activities.ExercisesView.Adapters.ExercisesAdapter;
import com.jds.fitnessjunkiess.getfitapp.Data.DataModels.Workout;
import com.jds.fitnessjunkiess.getfitapp.Data.ViewModels.ExerciseViewModel;
import com.jds.fitnessjunkiess.getfitapp.Dialogs.ExerciseFilterDialog;
import com.jds.fitnessjunkiess.getfitapp.Pojo.ExercisesFilter;
import com.jds.fitnessjunkiess.getfitapp.R;

public class ExercisesViewActivity extends AppCompatActivity {

  private ExercisesAdapter recyclerViewerAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercises_view);

    Workout activeWorkout = this.getActiveWorkout();
    ExercisesFilter exercisesFilter = this.getExercisesFilter();

    this.setUpActionBar();
    this.setUpRecyclerViewer();

    ExerciseViewModel exerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel.class);
    exerciseViewModel.setFilterMutableLiveData(exercisesFilter);
    exerciseViewModel.select().observe(this, exercises -> {
      if (exercises != null) {
        this.recyclerViewerAdapter.updateDataset(exercises);
      }
    });
  }

  private Workout getActiveWorkout() {
    return getIntent().getExtras().getParcelable("activeWorkout");
  }

  private ExercisesFilter getExercisesFilter() {
    ExercisesFilter exercisesFilter = getIntent().getExtras().getParcelable("exerciseFilters");
    if (exercisesFilter == null) {
      exercisesFilter = new ExercisesFilter("", "");
    }

    return exercisesFilter;
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
    this.recyclerViewerAdapter = new ExercisesAdapter();
    recyclerView.setAdapter(recyclerViewerAdapter);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.activity_exercises_view, menu);

    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
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

  public void openFiltersDialog() {
    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    ExerciseFilterDialog dialogTest = new ExerciseFilterDialog();
    dialogTest.show(fragmentTransaction, "135555");
  }
}
