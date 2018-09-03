package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.ExercisesHomeScreen;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jds.fitnessjunkiess.getfitapp.Activities.ExercisesView.ExercisesViewActivity;
import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.ExercisesHomeScreen.Adapters.MuscleGroupsAdapter;
import com.jds.fitnessjunkiess.getfitapp.Data.DataModels.Exercise;
import com.jds.fitnessjunkiess.getfitapp.Data.ViewModels.MuscleGroupViewModel;
import com.jds.fitnessjunkiess.getfitapp.Pojo.ExercisesFilter;
import com.jds.fitnessjunkiess.getfitapp.R;

public class ExercisesHomeScreenFragment extends Fragment implements View.OnClickListener {

  private final String TAG = "EXERCISES SCREEN";

  private MuscleGroupViewModel muscleGroupViewModel;
  private MuscleGroupsAdapter muscleGroupsAdapter;

  public ExercisesHomeScreenFragment() {}

  public static ExercisesHomeScreenFragment getInstance() {
    return new ExercisesHomeScreenFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    this.muscleGroupsAdapter = new MuscleGroupsAdapter(getContext());

    this.muscleGroupViewModel = ViewModelProviders.of(this).get(MuscleGroupViewModel.class);
    muscleGroupViewModel.selectAll().observe(this, muscleGroups -> {
      if (muscleGroups != null) {
        Log.d(TAG, "onCreate: Displaying exercises muscle groups" + muscleGroups.toString());
        this.muscleGroupsAdapter.updateDataSet(muscleGroups);
      }
    });
  }

  @Override
  public View onCreateView(
    LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_exercises_list, container, false);
    Button allExercises = view.findViewById(R.id.view_all_exercises_btn);
    Button biceps = view.findViewById(R.id.view_biceps_exercises_btn);

    allExercises.setOnClickListener(this);
    biceps.setOnClickListener(this);

    return view;
  }

  @Override
  public void onClick(View v) {
    ExercisesFilter exercisesFilter = new ExercisesFilter();

    switch (v.getId()) {
      case R.id.view_all_exercises_btn:
        break;
      case R.id.view_weights_exercises_btn:
        exercisesFilter.type = "Weights";
        break;
      case R.id.view_body_weight_exercises_btn:
        break;
      case R.id.view_cardio_exercises_btn:
        break;
      case R.id.view_custom_exercises_btn:
        break;
      case R.id.view_abs_exercises_btn:
        break;
      case R.id.view_biceps_exercises_btn:
        exercisesFilter.muscleGroup = "Biceps";
        break;
    }

    this.openExercisesViewActivity(exercisesFilter);
  }

  private void openExercisesViewActivity(ExercisesFilter exercisesFilter) {
    Intent intent = new Intent(getContext(), ExercisesViewActivity.class);
    intent.putExtra("exerciseFilters", exercisesFilter);
    startActivity(intent);
  }
}
