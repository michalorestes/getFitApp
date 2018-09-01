package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Exercises;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jds.fitnessjunkiess.getfitapp.Data.ViewModels.MuscleGroupViewModel;
import com.jds.fitnessjunkiess.getfitapp.R;

public class ExercisesListFragment extends Fragment {

  private MuscleGroupViewModel muscleGroupViewModel;
  private final String TAG = "EXERCISES SCREEN";
  public ExercisesListFragment() {}

  public static ExercisesListFragment getInstance() {
    return new ExercisesListFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    this.muscleGroupViewModel = ViewModelProviders.of(this).get(MuscleGroupViewModel.class);

    muscleGroupViewModel.selectAll().observe(this, muscleGroups -> {
      if (muscleGroups != null) {
        Log.d(TAG, "onCreate: Displaying exercises muscle groups" + muscleGroups.toString());
      }
    });

  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    return inflater.inflate(R.layout.fragment_exercises_list, container, false);
  }
}
