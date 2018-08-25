package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Workouts;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Workouts.Adapters.WorkoutListRecycleViewAdapter;
import com.jds.fitnessjunkiess.getfitapp.Dialogs.WorkoutDetailsDialog;
import com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutViewActivity.WorkoutViewActivity;
import com.jds.fitnessjunkiess.getfitapp.Data.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;
import com.jds.fitnessjunkiess.getfitapp.Data.ViewModels.WorkoutsViewModel;
import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Workouts.Adapters.*;

import java.util.List;

public class WorkoutsListFragment extends Fragment
    implements View.OnClickListener, WorkoutListViewHolder.OnSelectedInterface, WorkoutDetailsDialog.ActionsInterface {

  private static final String TAG = "WORKOUT_LIST_FRAGMENT";

  private WorkoutsViewModel workoutsViewModel;
  private WorkoutListRecycleViewAdapter recycleViewAdapter;
  private RecyclerView recyclerView;

  public WorkoutsListFragment() {}

  public static WorkoutsListFragment getInstance() {
    return new WorkoutsListFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    this.workoutsViewModel = ViewModelProviders.of(this).get(WorkoutsViewModel.class);

    this.workoutsViewModel.getData().observe(this, new Observer<List<Workout>>() {
      @Override
      public void onChanged(@Nullable List<Workout> workouts) {
        if (workouts != null) {
          recycleViewAdapter.updateDataSet(workouts);
        }
      }
    });
  }

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_workouts_list, container, false);
    this.recyclerView = view.findViewById(R.id.workoutsList);

    RecyclerView.LayoutManager recyclerViewLayoutManager = new LinearLayoutManager(getContext());
    recyclerView.setLayoutManager(recyclerViewLayoutManager);

    this.recycleViewAdapter = new WorkoutListRecycleViewAdapter(this);
    recyclerView.setAdapter(this.recycleViewAdapter);

    FloatingActionButton actionButton = view.findViewById(R.id.floating_action_add_workout);
    actionButton.setOnClickListener(this);

    return view;
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

  @Override
  public void onWorkoutSelected(Workout workout) {
    this.startWorkoutViewActivity(workout);
  }

  @Override
  public void onSaveAction(Workout result) {
    this.workoutsViewModel.insert(result);
    this.recyclerView.smoothScrollToPosition(this.recycleViewAdapter.getItemCount()-1);
  }

  private void startWorkoutViewActivity(Workout workout) {
    Intent workoutView = new Intent(getContext(), WorkoutViewActivity.class);
    workoutView.putExtra("workoutData", workout);
    startActivity(workoutView);
  }
}
