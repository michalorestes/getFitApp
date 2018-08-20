package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Exercises;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jds.fitnessjunkiess.getfitapp.R;

public class ExercisesListFragment extends Fragment {

  public ExercisesListFragment() {}

  public static ExercisesListFragment getInstance() {
    return new ExercisesListFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    return inflater.inflate(R.layout.fragment_exercises_list, container, false);
  }
}
