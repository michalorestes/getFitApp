package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.ExercisesHomeScreen;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.ExercisesHomeScreen.Adapters.MuscleGroupsAdapter;
import com.jds.fitnessjunkiess.getfitapp.Data.ViewModels.MuscleGroupViewModel;
import com.jds.fitnessjunkiess.getfitapp.R;

public class ExercisesHomeScreenFragment extends Fragment {

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
//
//    GridView gridView = view.findViewById(R.id.muscle_group_grid_view);
//    gridView.setAdapter(this.muscleGroupsAdapter);

    return view;
  }
}
