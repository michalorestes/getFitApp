package com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutViewActivity.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutViewActivity.Adapters.WorkoutExerciseListInterface;
import com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutViewActivity.WorkoutViewActivity;
import com.jds.fitnessjunkiess.getfitapp.R;

public class WorkoutExercisesFragment extends Fragment {

  private WorkoutExerciseListInterface WorkoutExerciseListInterface;

  public WorkoutExercisesFragment() {}

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);

    if (context instanceof WorkoutViewActivity) {
      this.WorkoutExerciseListInterface = (WorkoutExerciseListInterface) context;
    }
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_worktout_exercises, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    View rootView = view;

    super.onViewCreated(view, savedInstanceState);
  }
}
