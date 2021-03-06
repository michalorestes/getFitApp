package com.jds.fitnessjunkiess.getfitapp.features.workout;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jds.fitnessjunkiess.getfitapp.features.workout.adapters.WorkoutListRecycleViewAdapter;
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;
import com.jds.fitnessjunkiess.getfitapp.data.viewModels.WorkoutsViewModel;

public class WorkoutsListFragment extends Fragment implements View.OnClickListener {

  private static final String TAG = "WORKOUT_LIST_FRAGMENT";

  private WorkoutsViewModel workoutsViewModel;
  private WorkoutListRecycleViewAdapter recycleViewAdapter;
  private RecyclerView recyclerView;

  public WorkoutsListFragment() {}

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.d(TAG, "onCreate");
    this.workoutsViewModel = ViewModelProviders.of(this).get(WorkoutsViewModel.class);
  }

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_workouts_list, container, false);
    this.recyclerView = view.findViewById(R.id.workoutsList);

    RecyclerView.LayoutManager recyclerViewLayoutManager = new LinearLayoutManager(getContext());
    recyclerView.setLayoutManager(recyclerViewLayoutManager);

    this.recycleViewAdapter = new WorkoutListRecycleViewAdapter();
    recyclerView.setAdapter(this.recycleViewAdapter);

    FloatingActionButton actionButton = view.findViewById(R.id.floating_action_add_workout);
    actionButton.setOnClickListener(this);

    return view;
  }

  @Override
  public void onStart() {
    super.onStart();
    this.workoutsViewModel.getData().observe(this, workouts -> {
      if (workouts != null) {
        recycleViewAdapter.updateDataSet(workouts);
      }
    });
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.floating_action_add_workout:
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        WorkoutDetailsDialog dialogTest = new WorkoutDetailsDialog();
        dialogTest.show(fragmentTransaction, "tag");
        break;
    }
  }
}
