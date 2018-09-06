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
import com.jds.fitnessjunkiess.getfitapp.Data.DataModels.MuscleGroup;
import com.jds.fitnessjunkiess.getfitapp.Data.ViewModels.MuscleGroupViewModel;
import com.jds.fitnessjunkiess.getfitapp.Pojo.ExerciseTypes;
import com.jds.fitnessjunkiess.getfitapp.Pojo.ExercisesFilter;
import com.jds.fitnessjunkiess.getfitapp.Pojo.MuscleGroups;
import com.jds.fitnessjunkiess.getfitapp.R;

import java.util.ArrayList;

public class ExercisesHomeScreenFragment extends Fragment implements View.OnClickListener {

  private final String TAG = "EXERCISES SCREEN";

  public ExercisesHomeScreenFragment() {}

  public static ExercisesHomeScreenFragment getInstance() {
    return new ExercisesHomeScreenFragment();
  }

  @Override
  public View onCreateView(
    LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_exercises_list, container, false);

    Button allExercises = view.findViewById(R.id.view_all_exercises_btn);

    Button weights = view.findViewById(R.id.view_weights_exercises_btn);
    Button bodyWeights = view.findViewById(R.id.view_body_weight_exercises_btn);
    Button cardio = view.findViewById(R.id.view_cardio_exercises_btn);
    Button customExercises = view.findViewById(R.id.view_custom_exercises_btn);

    Button abs = view.findViewById(R.id.view_abs_exercises_btn);
    Button biceps = view.findViewById(R.id.view_biceps_exercises_btn);
    Button back = view.findViewById(R.id.view_back_exercises_btn);
    Button legs = view.findViewById(R.id.view_legs_exercises_btn);
    Button triceps = view.findViewById(R.id.view_triceps_exercises_btn);
    Button chest = view.findViewById(R.id.view_chest_exercises_btn);

    allExercises.setOnClickListener(this);
    bodyWeights.setOnClickListener(this);
    weights.setOnClickListener(this);
    cardio.setOnClickListener(this);
    customExercises.setOnClickListener(this);

    abs.setOnClickListener(this);
    biceps.setOnClickListener(this);
    back.setOnClickListener(this);
    legs.setOnClickListener(this);
    triceps.setOnClickListener(this);
    chest.setOnClickListener(this);

    return view;
  }

  @Override
  public void onClick(View v) {
    ExercisesFilter exercisesFilter = new ExercisesFilter();

    switch (v.getId()) {
      case R.id.view_all_exercises_btn:
        exercisesFilter.types.add(ExerciseTypes.ALL);
        break;
      case R.id.view_weights_exercises_btn:
        exercisesFilter.types.add(ExerciseTypes.WEIGHTS);
        break;
      case R.id.view_body_weight_exercises_btn:
        exercisesFilter.types.add(ExerciseTypes.BODY_WEIGHT);
        break;
      case R.id.view_cardio_exercises_btn:
        exercisesFilter.types.add(ExerciseTypes.CARDIO);
        break;
      case R.id.view_custom_exercises_btn:
        //TODO: How will custom exercises work???
        break;
      case R.id.view_abs_exercises_btn:
        exercisesFilter.muscleGroup.add(MuscleGroups.ABS);
        break;
      case R.id.view_biceps_exercises_btn:
        exercisesFilter.muscleGroup.add(MuscleGroups.BICEPS);
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
