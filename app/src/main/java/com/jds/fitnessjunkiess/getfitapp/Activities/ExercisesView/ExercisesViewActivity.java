package com.jds.fitnessjunkiess.getfitapp.Activities.ExercisesView;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.jds.fitnessjunkiess.getfitapp.Activities.ExercisesView.Adapters.ExercisesAdapter;
import com.jds.fitnessjunkiess.getfitapp.Data.ViewModels.ExerciseViewModel;
import com.jds.fitnessjunkiess.getfitapp.R;

public class ExercisesViewActivity extends AppCompatActivity implements View.OnClickListener {

  private ExerciseViewModel exerciseViewModel;
  private ExercisesAdapter recyclerViewerAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercises_view);

    Toolbar toolbar = findViewById(R.id.toolbar_exercise_view_activity);
    toolbar.setTitle("Exercises");
    setSupportActionBar(toolbar);

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setDisplayShowHomeEnabled(true);
    }

    RecyclerView recyclerView = findViewById(R.id.exercise_view_recycle_viewer);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
    recyclerView.setLayoutManager(layoutManager);
    this.recyclerViewerAdapter = new ExercisesAdapter();
    recyclerView.setAdapter(recyclerViewerAdapter);

    this.exerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel.class);
    this.exerciseViewModel.setFilters("", "");
//    this.exerciseViewModel.selectAll();
    this.exerciseViewModel.select().observe(this, exercises -> {
      if (exercises != null) {
        this.recyclerViewerAdapter.updateDataset(exercises);
      }
    });

    Button button = findViewById(R.id.test_button);
    button.setOnClickListener(this);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        break;
    }

    return true;
  }

  @Override
  public void onClick(View v) {
    this.exerciseViewModel.setFilters("Biceps", "weight");
    this.exerciseViewModel.select().observe(this, exercises -> {
      if (exercises != null) {
        this.recyclerViewerAdapter.updateDataset(exercises);
      }
    });
  }
}
